package com.conceptile.com.quizeapp.dto;

import lombok.Data;

@Data
public class UserPerformanceDTO {
	private int totalQuestionsAttempted;
	private int correctAnswers;
	private double overallScorePercentage;
}
