package com.conceptile.com.quizeapp.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Component
public class UserQuiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	private Long questionId;
	private boolean isCorrect;
}
