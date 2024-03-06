package com.jyujyu_dayonetest.service;

import com.jyujyu_dayonetest.repository.StudentFailRepository;
import com.jyujyu_dayonetest.repository.StudentPassRepository;
import com.jyujyu_dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StudentScoreServiceMockTest {

    @Test
    @DisplayName("첫 번째 Mock Test")
    public void firstSaveScoreMockTest() throws Exception {
        // given
        StudentScoreService studentScoreService = new StudentScoreService(
            Mockito.mock(StudentScoreRepository.class),
            Mockito.mock(StudentPassRepository.class),
            Mockito.mock(StudentFailRepository.class)
        );

        String givenStudentName = "koo";
        String givenExam = "testExam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;

        // when
        studentScoreService.saveScore(
            givenStudentName,
            givenExam,
            givenKorScore,
            givenEnglishScore,
            givenMathScore
        );

        // then

    }
}
