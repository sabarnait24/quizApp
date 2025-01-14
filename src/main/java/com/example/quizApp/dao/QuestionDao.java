package com.example.quizApp.dao;

import com.example.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> getQuestionsByCategory(String category);

    @Query(value = "SELECT * FROM QUESTION  WHERE  Category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> createRandomQuizByCategory(int numQ, String category);
}
