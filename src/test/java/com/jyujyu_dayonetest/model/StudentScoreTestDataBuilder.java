package com.jyujyu_dayonetest.model;

public class StudentScoreTestDataBuilder {

    /**
     * 2가지 상태가 존재 1. passed * 세 점수의 합이 60점 이상일 때 2. failed * 세 점수의 합이 60점 미만일 때
     */
    public static StudentScore.StudentScoreBuilder passed() {
        return StudentScore
            .builder()
            .korScore(80)
            .englishScore(100)
            .mathScore(90)
            .studentName("defaultName")
            .exam("defaultExam");
    }

    public static StudentScore.StudentScoreBuilder failed() {
        return StudentScore
            .builder()
            .korScore(50)
            .englishScore(60)
            .mathScore(30)
            .studentName("defaultName")
            .exam("defaultExam");
    }
}
//}
