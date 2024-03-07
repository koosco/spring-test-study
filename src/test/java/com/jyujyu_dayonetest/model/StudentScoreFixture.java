package com.jyujyu_dayonetest.model;

public class StudentScoreFixture {

    public static StudentScore pased() {
        return StudentScore
            .builder()
            .korScore(80)
            .englishScore(100)
            .mathScore(90)
            .studentName("pass Name")
            .exam("default exam")
            .build();
    }

    public static StudentScore failed() {
        return StudentScore
            .builder()
            .korScore(20)
            .englishScore(30)
            .mathScore(10)
            .studentName("fail Name")
            .exam("default exam")
            .build();
    }

}
