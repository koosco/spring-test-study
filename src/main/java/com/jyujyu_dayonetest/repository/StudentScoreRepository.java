package com.jyujyu_dayonetest.repository;

import com.jyujyu_dayonetest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {

}
