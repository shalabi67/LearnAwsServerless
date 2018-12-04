package com.serverless.first;

public class ResponseClass {
    private String greetings;

    public ResponseClass(String greetings) {
        this.greetings = greetings;
    }
    public ResponseClass() {
        greetings ="nothing";
    }

    public String getGreetings() {
        return greetings;
    }
}
