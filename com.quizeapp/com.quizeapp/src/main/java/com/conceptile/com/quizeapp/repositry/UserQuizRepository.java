package com.conceptile.com.quizeapp.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conceptile.com.quizeapp.entity.UserQuiz;
@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {
}
