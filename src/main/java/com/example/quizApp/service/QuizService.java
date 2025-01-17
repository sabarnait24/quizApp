package com.example.quizApp.service;

import com.example.quizApp.dao.QuestionDao;
import com.example.quizApp.dao.QuizDao;
import com.example.quizApp.model.Question;
import com.example.quizApp.model.QuestionWrapper;
import com.example.quizApp.model.Quiz;
import com.example.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String title, int numQ, String category) {
        try {
            List<Question> questions = questionDao.createRandomQuizByCategory(numQ, category);
            Quiz quiz=new Quiz();

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

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        try{
            Optional<Quiz> quiz= quizDao.findById(id);
            if(quiz.isPresent()){
                List<QuestionWrapper> questionWrappers=new ArrayList<>();
                for(Question q:quiz.get().getQuestions()){
                    QuestionWrapper questionWrapper=new QuestionWrapper();
                    questionWrapper.setQuestionTitle(q.getQuestionTitle());
                    questionWrapper.setOption1(q.getOption1());
                    questionWrapper.setOption2(q.getOption2());
                    questionWrapper.setOption3(q.getOption3());
                    questionWrapper.setOption4(q.getOption4());
                    questionWrappers.add(questionWrapper);
                }
                return new ResponseEntity<>(questionWrappers,HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(null,HttpStatusCode.valueOf(400));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatusCode.valueOf(400));
        }

    }

    public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
        try{
            Optional<Quiz> quiz= quizDao.findById(id);

            if(quiz.isPresent()){
                int score=0;
                List<Question> questions=quiz.get().getQuestions();
                for(Response response:responses){
                    Integer Id=response.getId();
                    String answer=response.getResponse();

                    String correctAnswer=questions.stream().filter(q->q.getId()==Id).findFirst().get().getCorrectAnswer();

                    if (answer.equals(correctAnswer)){
                        score++;
                    }
                }
                return new ResponseEntity<>(score,HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(null,HttpStatusCode.valueOf(400));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatusCode.valueOf(400));
        }
    }
}

