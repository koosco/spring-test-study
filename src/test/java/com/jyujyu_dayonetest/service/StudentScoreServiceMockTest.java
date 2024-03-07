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

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이상인 경우")
    public void saveScoreMockTest() throws Exception {
        // given : 평균 점수가 60점 이상인 경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
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

        // then : 값을 넣었을 때 각 mock 객체가 호출되는 횟수를 검증
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());

    }

    @Test
    @DisplayName("성적 저장 로직 / 60점 미만인 경우")
    public void saveScoreMockTest2() throws Exception {
        // given : 평균 점수가 60점 미만인 경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        );

        String givenStudentName = "koo";
        String givenExam = "testExam";
        Integer givenKorScore = 20;
        Integer givenEnglishScore = 30;
        Integer givenMathScore = 40;

        // when
        studentScoreService.saveScore(
            givenStudentName,
            givenExam,
            givenKorScore,
            givenEnglishScore,
            givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any());
    }
}
