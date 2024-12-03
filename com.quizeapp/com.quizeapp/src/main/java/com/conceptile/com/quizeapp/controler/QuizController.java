package com.conceptile.com.quizeapp.controler;

import com.conceptile.com.quizeapp.dto.QuestionDTO;
import com.conceptile.com.quizeapp.dto.SubmitAnswerDTO;
import com.conceptile.com.quizeapp.dto.UserPerformanceDTO;
import com.conceptile.com.quizeapp.entity.Question;
import com.conceptile.com.quizeapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/add-question")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		Question savedQuestion = quizService.saveQuestion(question);
		return ResponseEntity.ok(savedQuestion);
	}

	@PostMapping("/take/{userId}")
	public ResponseEntity<QuestionDTO> takeQuiz(@PathVariable Long userId) {
		QuestionDTO question = quizService.getRandomQuestion(userId);
		return ResponseEntity.ok(question);
	}

	@GetMapping("/dashboard/{userId}")
	public ResponseEntity<UserPerformanceDTO> getDashboard(@PathVariable Long userId) {
		UserPerformanceDTO performance = quizService.endQuiz(userId);
		return ResponseEntity.ok(performance);
	}

	@PostMapping("/submit/{userId}")
	public ResponseEntity<Map<String, Object>> submitAnswer(@PathVariable Long userId,
			@RequestBody SubmitAnswerDTO submitAnswerDTO) {
		boolean isCorrect = quizService.submitAnswer(userId, submitAnswerDTO);
		Map<String, Object> response = new HashMap<>();
		response.put("correct", isCorrect);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/end/{userId}")
	public ResponseEntity<UserPerformanceDTO> endQuiz(@PathVariable Long userId) {
		UserPerformanceDTO performance = quizService.endQuiz(userId);
		return ResponseEntity.ok(performance);
	}

}
