package com.example.quizApp.controller;

import com.example.quizApp.model.Question;
import com.example.quizApp.model.QuestionWrapper;
import com.example.quizApp.model.Response;
import com.example.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/quiz/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.submitQuiz(id,responses);
    }

}
