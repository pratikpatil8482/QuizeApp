package com.conceptile.com.quizeapp.service;

import com.conceptile.com.quizeapp.dto.QuestionDTO;
import com.conceptile.com.quizeapp.dto.SubmitAnswerDTO;
import com.conceptile.com.quizeapp.dto.UserPerformanceDTO;
import com.conceptile.com.quizeapp.entity.Question;
import com.conceptile.com.quizeapp.entity.User;
import com.conceptile.com.quizeapp.entity.UserQuiz;
import com.conceptile.com.quizeapp.repositry.QuestionRepository;
import com.conceptile.com.quizeapp.repositry.UserQuizRepository;
import com.conceptile.com.quizeapp.repositry.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserQuizRepository userQuizRepository;

    // Fetch a random question for the user
    public QuestionDTO getRandomQuestion(Long userId) {
        long questionCount = questionRepository.count();
        if (questionCount == 0) {
            throw new RuntimeException("No questions available.");
        }

        long randomId = new Random().nextLong(questionCount) + 1;
        Optional<Question> questionOptional = questionRepository.findById(randomId);
        if (questionOptional.isEmpty()) {
            throw new RuntimeException("Question not found.");
        }

        Question question = questionOptional.get();
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setQuestionText(question.getQuestionText());
        dto.setOptionA(question.getOptionA());
        dto.setOptionB(question.getOptionB());
        dto.setOptionC(question.getOptionC());
        dto.setOptionD(question.getOptionD());
        return dto;
    }

    // Submit the answer for a question
    public boolean submitAnswer(Long userId, SubmitAnswerDTO submitAnswerDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }
        User user = userOptional.get();

        Optional<Question> questionOptional = questionRepository.findById(submitAnswerDTO.getQuestionId());
        if (questionOptional.isEmpty()) {
            throw new RuntimeException("Question not found.");
        }
        Question question = questionOptional.get();

        boolean isCorrect = question.getCorrectOption().equals(submitAnswerDTO.getSelectedOption());
        user.setTotalQuestionsAttempted(user.getTotalQuestionsAttempted() + 1);
        if (isCorrect) {
            user.setCorrectAnswers(user.getCorrectAnswers() + 1);
        }
        userRepository.save(user);

        UserQuiz userQuiz = new UserQuiz();
        userQuiz.setUser(user);
        userQuiz.setQuestionId(question.getId());
        userQuiz.setCorrect(isCorrect);
        userQuizRepository.save(userQuiz);

        return isCorrect;
    }

    // Calculate and fetch user's performance data
    public UserPerformanceDTO endQuiz(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }
        User user = userOptional.get();

        UserPerformanceDTO performanceDTO = new UserPerformanceDTO();
        performanceDTO.setTotalQuestionsAttempted(user.getTotalQuestionsAttempted());
        performanceDTO.setCorrectAnswers(user.getCorrectAnswers());
        performanceDTO.setOverallScorePercentage(
            user.getTotalQuestionsAttempted() > 0
                ? (user.getCorrectAnswers() * 100.0) / user.getTotalQuestionsAttempted()
                : 0.0
        );
        return performanceDTO;
    }

    // Save a new question to the repository
    public Question saveQuestion(Question question) {
        if (question.getQuestionText() == null || question.getCorrectOption() == null) {
            throw new RuntimeException("Question text and correct option cannot be null.");
        }
        return questionRepository.save(question);
    }
}
