package com.conceptile.com.quizeapp.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int totalQuestionsAttempted;
    private int correctAnswers;
}
