package com.conceptile.com.quizeapp.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conceptile.com.quizeapp.entity.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
