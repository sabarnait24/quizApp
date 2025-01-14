package com.example.quizApp.service;

import com.example.quizApp.dao.QuestionDao;
import com.example.quizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.getQuestionsByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public void addQuestion(Question question) {
            questionDao.save(question);
    }

    public  ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted  Sucessfully",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("exception is " + e);
        }
        return new ResponseEntity<>("Question Not deleted ",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addSingleQuestion(Question question) {
        try {
            addQuestion(question);
            return new ResponseEntity<>("Question added Sucessfully",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("exception is " + e);
        }
        return new ResponseEntity<>("Question Not added",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addMultipleQuestion(List<Question> question) {
        try {
            for (int i = 0; i < question.size() ; i++) {
                addQuestion(question.get(i));
            }
            return new ResponseEntity<>("Question added Sucessfully",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("exception is " + e);
        }
        return new ResponseEntity<>("Question Not added",HttpStatus.BAD_REQUEST);
    }
}
