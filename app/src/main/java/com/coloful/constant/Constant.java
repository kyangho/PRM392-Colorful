package com.coloful.constant;

public class Constant {

    public enum System {
        JSON_ACCOUNT("JSON_ACCOUNT");
        private final String value;

        System(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Account {
        ID("id"),
        USERNAME("username"),
        PASSWORD("password"),
        DOB("dob"),
        EMAIL("email"),
        TABLE_NAME("Account");

        private final String value;

        Account(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Quiz {
        ID("id"),
        TITLE("title"),
        AUTHOR("author"),
        TABLE_NAME("Quiz");

        private final String value;

        Quiz(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Question {
        ID("id"),
        Content("question_content"),
        QUIZ_ID("quiz_id"),
        CORRECT_ANSWER("correct_answer"),
        TABLE_NAME("Question");
        private final String value;

        Question(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Answer {
        ID("id"),
        Content("question_content"),
        QUES_ID("question_id"),
        TABLE_NAME("Answer"),
        ;
        private final String value;

        Answer(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum QuizAccount {
        QUIZ_ID("quiz_id"),
        ACCOUNT_ID("account_id"),
        TABLE_NAME("Quiz_Account"),
        ;
        private final String value;

        QuizAccount(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
