package com.coloful.model;

public class Question {
    private String content;
    private String answer;

    public Question() {
    }

    public Question(String content, String answer) {
        this.content = content;
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return content + "\n\n" + answer ;
    }
}
