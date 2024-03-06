package com.jyujyu_dayonetest.controller;

import com.jyujyu_dayonetest.controller.request.SaveExamScoreRequest;
import com.jyujyu_dayonetest.controller.response.ExamFailStudentResponse;
import com.jyujyu_dayonetest.controller.response.ExamPassStudentResponse;
import com.jyujyu_dayonetest.service.StudentScoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoreApi {

    private final StudentScoreService studentScoreService;

    @PutMapping("/exam/{exam}/score")
    public void save(
        @PathVariable("exam") String exam,
        @RequestBody SaveExamScoreRequest request) {
        studentScoreService.saveScore(
            request.getStudentName(),
            exam,
            request.getEnglishScore(),
            request.getEnglishScore(),
            request.getMathScore());
    }

    @GetMapping("/exam/{exam}/pass")
    public List<ExamPassStudentResponse> pass(@PathVariable("exam") String exam) {
        return studentScoreService.getPassStudentsList(exam);
    }

    @GetMapping("/exam/{exam}/fail")
    public List<ExamFailStudentResponse> fail(@PathVariable("exam") String exam) {
        return studentScoreService.getFailStudentsList(exam);
    }
}
