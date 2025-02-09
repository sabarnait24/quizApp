package com.example.quizApp.controller;


import com.example.quizApp.model.Question;
import com.example.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
       return questionService.getAllQuestions();
    }

    @GetMapping("/question/{category}")
    public  ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/question/addSingleQuestion")
    public  ResponseEntity<String> addSingleQuestion(@RequestBody Question question){
        System.out.println(question);
        return questionService.addSingleQuestion(question);

    }

    @PostMapping("/question/addMultipleQuestion")
    public  ResponseEntity<String> addMultipleQuestion(@RequestBody List<Question> questions){
        System.out.println("before");
        questions.forEach(Question::toString);
        System.out.println("after");
        return  questionService.addMultipleQuestion(questions);

    }
}
