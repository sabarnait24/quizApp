package com.example.quizApp.controller;

import com.example.quizApp.model.Question;
import com.example.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/quiz/create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, int numQ, String category){
        return quizService.createQuiz(title,numQ,category);
    }

}
