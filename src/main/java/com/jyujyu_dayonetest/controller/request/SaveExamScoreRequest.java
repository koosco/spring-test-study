package com.jyujyu_dayonetest.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
public class SaveExamScoreRequest {

    private String studentName;
    private Integer korScore;
    private Integer englishScore;
    private Integer mathScore;
}