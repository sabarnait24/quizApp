package com.example.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String category;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + Id +
                ", category='" + category + '\'' +
                '}';
    }

}
