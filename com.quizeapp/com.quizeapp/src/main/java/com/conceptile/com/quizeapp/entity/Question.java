package com.conceptile.com.quizeapp.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Component
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;
}
