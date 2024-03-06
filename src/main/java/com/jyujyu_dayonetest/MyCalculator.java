package com.jyujyu_dayonetest;

public class MyCalculator {
    private Double result;

    public MyCalculator() {
        this.result = 0.0;
    }

    public MyCalculator(Double initResult) {
        this.result = initResult;
    }

    public MyCalculator add(Double num) {
        this.result += num;
        return this;
    }

    public MyCalculator minus(Double num) {
        this.result -= num;
        return this;
    }

    public MyCalculator multiply(Double num) {
        this.result *= num;
        return this;
    }

    public MyCalculator divide(Double num) {
        if (num == 0.0) {
            throw new ZeroDivisionException();
        }
        this.result /= num;
        return this;
    }

    public Double getResult() {
        return result;
    }

    public static class ZeroDivisionException extends RuntimeException {


    }
}
