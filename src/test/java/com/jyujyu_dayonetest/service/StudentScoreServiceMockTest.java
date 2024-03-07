package com.jyujyu_dayonetest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.jyujyu_dayonetest.MyCalculator;
import com.jyujyu_dayonetest.controller.response.ExamFailStudentResponse;
import com.jyujyu_dayonetest.controller.response.ExamPassStudentResponse;
import com.jyujyu_dayonetest.model.StudentFail;
import com.jyujyu_dayonetest.model.StudentPass;
import com.jyujyu_dayonetest.model.StudentScore;
import com.jyujyu_dayonetest.repository.StudentFailRepository;
import com.jyujyu_dayonetest.repository.StudentPassRepository;
import com.jyujyu_dayonetest.repository.StudentScoreRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

        // StudentScore에 대한 ArgumentCaptor 생성
        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(
            StudentScore.class);

        // StudenPass에 대한 ArgumentCaptor 생성
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(
            StudentPass.class
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

        StudentScore expectStudentScore = StudentScore
            .builder()
            .studentName(givenStudentName)
            .exam(givenExam)
            .korScore(givenKorScore)
            .englishScore(givenEnglishScore)
            .mathScore(givenMathScore)
            .build();

        StudentPass expectStudentPass = StudentPass.builder()
            .studentName(givenStudentName)
            .exam(givenExam)
            .avgScore(
                (new MyCalculator(0.0)
                    .add(givenKorScore.doubleValue())
                    .add(givenEnglishScore.doubleValue())
                    .add(givenMathScore.doubleValue())
                    .divide(3.0)
                    .getResult())
            ).build();

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
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        );

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(
            StudentScore.class
        );
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(
            StudentFail.class
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

        StudentScore expectStudentScore = StudentScore.builder()
            .studentName(givenStudentName)
            .exam(givenExam)
            .korScore(givenKorScore)
            .englishScore(givenEnglishScore)
            .mathScore(givenMathScore)
            .build();

        StudentFail expectStudentFail = StudentFail.builder()
            .studentName(givenStudentName)
            .exam(givenExam)
            .avgScore(
                (new MyCalculator(0.0)
                    .add(givenKorScore.doubleValue())
                    .add(givenEnglishScore.doubleValue())
                    .add(givenMathScore.doubleValue())
                    .divide(3.0)
                    .getResult())
            ).build();

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
        assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());
        assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());

        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(studentFailArgumentCaptor.capture());
        StudentFail capturedStudentFail = studentFailArgumentCaptor.getValue();
        assertEquals(expectStudentFail.getStudentName(), capturedStudentFail.getStudentName());
        assertEquals(expectStudentFail.getExam(), capturedStudentFail.getExam());
        assertEquals(expectStudentFail.getAvgScore(), capturedStudentFail.getAvgScore());
    }

    @Test
    @DisplayName("합격자 명단 가져오기 검증")
    public void getPassStudentsListTest() throws Exception {
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentPass expectStudent1 = StudentPass.builder().id(1L).studentName("koo")
            .exam("testexam").avgScore(70.0).build();
        StudentPass expectStudent2 = StudentPass.builder().id(2L).studentName("test")
            .exam("testexam").avgScore(80.0).build();
        StudentPass expectStudent3 = StudentPass.builder().id(3L).studentName("iamnot")
            .exam("secondexam").avgScore(90.0).build();

        // findAll이 호출되었을 때 이 리스트를 리턴해라(findAll의 리턴값을 이 값으로 함)
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
            expectStudent1,
            expectStudent2,
            expectStudent3
        ));

        StudentScoreService studentScoreService = new StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        );
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

        expectResponses.forEach(response -> {
            System.out.println(response.getStudentName() + ", " + response.getAvgScore());
        });
        System.out.println("====");
        responses.forEach(response -> {
            System.out.println(response.getStudentName() + ", " + response.getAvgScore());
        });
    }

    @Test
    @DisplayName("불합격자 명단 가져오기")
    public void getFailStudentListTest() throws Exception {
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentFail expectStudent1 = StudentFail.builder().id(1L).studentName("koo")
            .exam("testexam").avgScore(20.0).build();
        StudentFail expectStudent2 = StudentFail.builder().id(2L).studentName("test")
            .exam("testexam").avgScore(30.0).build();
        StudentFail expectStudent3 = StudentFail.builder().id(3L).studentName("iamnot")
            .exam("secondexam").avgScore(90.0).build();

        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
            expectStudent1,
            expectStudent2,
            expectStudent3
        ));

        StudentScoreService studentScoreService = new StudentScoreService(
            studentScoreRepository,
            studentPassRepository,
            studentFailRepository
        );
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
