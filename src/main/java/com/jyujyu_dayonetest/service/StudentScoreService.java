package com.jyujyu_dayonetest.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentScoreService {

    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;
    private final StudentFailRepository studentFailRepository;

    public void saveScore(String studentName, String exam, Integer korScore, Integer englishScore,
        Integer mathScore) {
        StudentScore score = StudentScore.builder()
            .studentName(studentName)
            .exam(exam)
            .korScore(korScore)
            .englishScore(englishScore)
            .mathScore(mathScore)
            .build();
        studentScoreRepository.save(score);

        MyCalculator calculator = new MyCalculator(0.0);
        Double avgScore = calculator
            .add(korScore.doubleValue())
            .add(englishScore.doubleValue())
            .add(mathScore.doubleValue())
            .divide(3.0)
            .getResult();

        if (avgScore >= 60) {
            StudentPass studentPass = StudentPass.builder()
                .exam(exam)
                .studentName(studentName)
                .avgScore(avgScore)
                .build();
            studentPassRepository.save(studentPass);
        } else {
            StudentFail studentFail = StudentFail.builder()
                .exam(exam)
                .studentName(studentName)
                .avgScore(avgScore)
                .build();
            studentFailRepository.save(studentFail);
        }
    }

    public List<ExamPassStudentResponse> getPassStudentsList(String exam) {
        List<StudentPass> studentPasses = studentPassRepository.findAll();

        return studentPasses.stream()
            .filter((pass) -> pass.getExam().equals(exam))
            .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
            .toList();
    }

    public List<ExamFailStudentResponse> getFailStudentsList(String exam) {
        List<StudentFail> studentFails = studentFailRepository.findAll();

        return studentFails.stream()
            .filter(fail -> fail.getExam().equals(exam))
            .map(fail -> new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore()))
            .toList();
    }
}
