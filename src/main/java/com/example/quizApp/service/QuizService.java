package com.example.quizApp.service;

import com.example.quizApp.dao.QuestionDao;
import com.example.quizApp.dao.QuizDao;
import com.example.quizApp.model.Question;
import com.example.quizApp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    Quiz quiz;
    public ResponseEntity<String> createQuiz(String title, int numQ, String category) {
        try {
            List<Question> questions = questionDao.createRandomQuizByCategory(numQ, category);

            quiz.setTitle(title);
            quiz.setCategory(category);
            quiz.setQuestions(questions);

            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to create quiz", HttpStatusCode.valueOf(400));
    }
}

