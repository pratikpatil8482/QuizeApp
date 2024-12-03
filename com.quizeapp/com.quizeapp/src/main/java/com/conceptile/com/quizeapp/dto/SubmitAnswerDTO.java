package com.conceptile.com.quizeapp.dto;

import lombok.Data;

@Data
public class SubmitAnswerDTO {
	private Long questionId;
	private String selectedOption;
}
