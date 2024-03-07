package com.jyujyu_dayonetest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.jyujyu_dayonetest.controller.response.ExamFailStudentResponse;
import com.jyujyu_dayonetest.controller.response.ExamPassStudentResponse;
import com.jyujyu_dayonetest.model.StudentFail;
import com.jyujyu_dayonetest.model.StudentFailFixture;
import com.jyujyu_dayonetest.model.StudentPass;
import com.jyujyu_dayonetest.model.StudentPassFixture;
import com.jyujyu_dayonetest.model.StudentScore;
import com.jyujyu_dayonetest.model.StudentScoreFixture;
import com.jyujyu_dayonetest.model.StudentScoreTestDataBuilder;
import com.jyujyu_dayonetest.repository.StudentFailRepository;
import com.jyujyu_dayonetest.repository.StudentPassRepository;
import com.jyujyu_dayonetest.repository.StudentScoreRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class StudentScoreServiceMockTest {

    private StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private StudentPassRepository studentPassRepository;
    private StudentFailRepository studentFailRepository;

    @BeforeEach
    public void beforeEach() {
        studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        studentPassRepository = Mockito.mock(StudentPassRepository.class);
        studentFailRepository = Mockito.mock(StudentFailRepository.class);

        studentScoreService = new StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        );
    }

    @Test
    @DisplayName("첫 번째 Mock Test")
    public void firstSaveScoreMockTest() throws Exception {
        // given
        StudentScore score = StudentScoreFixture.pased();

        // when
        studentScoreService.saveScore(
            score.getStudentName(),
            score.getExam(),
            score.getKorScore(),
            score.getEnglishScore(),
            score.getMathScore()
        );
        // then

    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이상인 경우")
    public void saveScoreMockTest() throws Exception {
        // given : 평균 점수가 60점 이상인 경우
        StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed().build();
        StudentPass expectStudentPass = StudentPassFixture.create(expectStudentScore);

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(
            StudentScore.class);

        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(
            StudentPass.class
        );

        // when
        studentScoreService.saveScore(
            expectStudentScore.getStudentName(),
            expectStudentScore.getExam(),
            expectStudentScore.getKorScore(),
            expectStudentScore.getEnglishScore(),
            expectStudentScore.getMathScore()
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1))
            .save(studentScoreArgumentCaptor.capture());
        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
        assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());
        assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());

        Mockito.verify(studentPassRepository, Mockito.times(1))
            .save(studentPassArgumentCaptor.capture());
        StudentPass capturedStudentPass = studentPassArgumentCaptor.getValue();
        assertEquals(expectStudentPass.getStudentName(), capturedStudentPass.getStudentName());
        assertEquals(expectStudentPass.getExam(), capturedStudentPass.getExam());
        assertEquals(expectStudentPass.getAvgScore(), capturedStudentPass.getAvgScore());

        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 / 60점 미만인 경우")
    public void saveScoreMockTest2() throws Exception {
        // given : 평균 점수가 60점 미만인 경우
        StudentScore expectStudentScore = StudentScoreFixture.failed();
        StudentFail expectStudentFail = StudentFailFixture.create(expectStudentScore);

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(
            StudentScore.class
        );
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(
            StudentFail.class
        );

        // when
        studentScoreService.saveScore(
            expectStudentScore.getStudentName(),
            expectStudentScore.getExam(),
            expectStudentScore.getKorScore(),
            expectStudentScore.getEnglishScore(),
            expectStudentScore.getMathScore()
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1))
            .save(studentScoreArgumentCaptor.capture());
        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
        assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());
        assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());

        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1))
            .save(studentFailArgumentCaptor.capture());
        StudentFail capturedStudentFail = studentFailArgumentCaptor.getValue();
        assertEquals(expectStudentFail.getStudentName(), capturedStudentFail.getStudentName());
        assertEquals(expectStudentFail.getExam(), capturedStudentFail.getExam());
        assertEquals(expectStudentFail.getAvgScore(), capturedStudentFail.getAvgScore());
    }

    @Test
    @DisplayName("합격자 명단 가져오기 검증")
    public void getPassStudentsListTest() throws Exception {
        // given
        StudentPass expectStudent1 = StudentPassFixture.create("koo", "testexam");
        StudentPass expectStudent2 = StudentPassFixture.create("test", "testexam");
        StudentPass expectStudent3 = StudentPassFixture.create("iamnot", "secondexam");

        // findAll이 호출되었을 때 이 리스트를 리턴해라(findAll의 리턴값을 이 값으로 함)
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
            expectStudent1,
            expectStudent2,
            expectStudent3
        ));

        String givenTestExam = "testexam";

        // when
        List<ExamPassStudentResponse> responses = studentScoreService.getPassStudentsList(
            givenTestExam);

        // then
        List<ExamPassStudentResponse> expectResponses = List.of(expectStudent1, expectStudent2)
            .stream()
            .map(pass -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
            .toList();

        // then
        assertIterableEquals(expectResponses, responses);
    }

    @Test
    @DisplayName("불합격자 명단 가져오기")
    public void getFailStudentListTest() throws Exception {
        // given
        StudentFail expectStudent1 = StudentFailFixture.create("koo", "testexam");
        StudentFail expectStudent2 = StudentFailFixture.create("test", "testexam");
        StudentFail expectStudent3 = StudentFailFixture.create("iamnot", "secondexam");

        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
            expectStudent1,
            expectStudent2,
            expectStudent3
        ));

        String givenTestExam = "testexam";

        // when
        List<ExamFailStudentResponse> failStudentsList = studentScoreService.getFailStudentsList(
            givenTestExam);
        List<ExamFailStudentResponse> expectFailStudentsList = List.of(expectStudent1,
                expectStudent2).stream()
            .map(fail ->
                new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore()))
            .toList();

        // then
        assertIterableEquals(failStudentsList, expectFailStudentsList);
    }
}
