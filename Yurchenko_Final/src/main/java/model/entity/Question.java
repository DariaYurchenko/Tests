package model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    private Integer questionId;
    private String question;
    /**
     * percentOfRightAnswers - relation
     * of users' right answers to this question to all
     * users' answers
     */
    private Double percentOfRightAnswers;
    private String incorrectOption1;
    private String incorrectOption2;
    private String incorrectOption3;
    private String correctOption1;
    private String correctOption2;
    private String correctOption3;
    private QuestionType questionType;
    private Theme theme;

    private Question(Builder builder) {
        this.questionId = builder.id;
        this.question = builder.question;
        this.percentOfRightAnswers = builder.percentOfRightAnswers;
        this.incorrectOption1 = builder.incorrectOption1;
        this.incorrectOption2 = builder.incorrectOption2;
        this.incorrectOption3 = builder.incorrectOption3;
        this.correctOption1 = builder.correctOption1;
        this.correctOption2 = builder.correctOption2;
        this.correctOption3 = builder.correctOption3;
        this.questionType = builder.questionType;
        this.theme = builder.theme;

    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public Double getPercentOfRightAnswers() {
        return percentOfRightAnswers;
    }

    public String getIncorrectOption1() {
        return incorrectOption1;
    }

    public String getIncorrectOption2() {
        return incorrectOption2;
    }

    public String getIncorrectOption3() {
        return incorrectOption3;
    }

    public String getCorrectOption1() {
        return correctOption1;
    }

    public String getCorrectOption2() {
        return correctOption2;
    }

    public String getCorrectOption3() {
        return correctOption3;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public Theme getTheme() {
        return theme;
    }

    public static class Builder {
        private Integer id;
        private String question;
        private Double percentOfRightAnswers;
        private String incorrectOption1;
        private String incorrectOption2;
        private String incorrectOption3;
        private String correctOption1;
        private String correctOption2;
        private String correctOption3;
        private QuestionType questionType;
        private Theme theme;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder withPercentOfRightAnswers(Double percentOfRightAnswers) {
            this.percentOfRightAnswers = percentOfRightAnswers;
            return this;
        }

        public Builder withIncorrectOption1(String incorrectOption1) {
            this.incorrectOption1 = incorrectOption1;
            return this;
        }

        public Builder withIncorrectOption2(String incorrectOption2) {
            this.incorrectOption2 = incorrectOption2;
            return this;
        }

        public Builder withIncorrectOption3(String incorrectOption3) {
            this.incorrectOption3 = incorrectOption3;
            return this;
        }

        public Builder withCorrectOption1(String correctOption1) {
            this.correctOption1 = correctOption1;
            return this;
        }

        public Builder withCorrectOption2(String correctOption2) {
            this.correctOption2 = correctOption2;
            return this;
        }

        public Builder withCorrectOption3(String correctOption3) {
            this.correctOption3 = correctOption3;
            return this;
        }

        public Builder withQuestionType(QuestionType questionType) {
            this.questionType = questionType;
            return this;
        }

        public Builder withTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public Question build() {
            return new Question(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question questionToCompare = (Question) o;
        return Objects.equals(questionId, questionToCompare.questionId) &&
                Objects.equals(this.question, questionToCompare.question) &&
                Objects.equals(percentOfRightAnswers, questionToCompare.percentOfRightAnswers) &&
                Objects.equals(incorrectOption1, questionToCompare.incorrectOption1) &&
                Objects.equals(incorrectOption2, questionToCompare.incorrectOption2) &&
                Objects.equals(incorrectOption3, questionToCompare.incorrectOption3) &&
                Objects.equals(correctOption1, questionToCompare.correctOption1) &&
                Objects.equals(correctOption2, questionToCompare.correctOption2) &&
                Objects.equals(correctOption3, questionToCompare.correctOption3) &&
                Objects.equals(questionType, questionToCompare.questionType) &&
                Objects.equals(theme, questionToCompare.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, question, percentOfRightAnswers, incorrectOption1, incorrectOption2,
                incorrectOption3, correctOption1, correctOption2, correctOption3, questionType, theme);
    }

    @Override
    public String toString() {
        return new StringBuilder("{Question: ")
                .append("questionId = ").append(questionId)
                .append(", question = ").append(question)
                .append(",  percentOfRightAnswers = ").append(percentOfRightAnswers)
                .append(", incorrectOption1 = ").append(incorrectOption1)
                .append(", incorrectOption2 = ").append(incorrectOption2)
                .append(", incorrectOption3 = ").append(incorrectOption3)
                .append(", correctOption1 = ").append(correctOption1)
                .append(", correctOption2 = ").append(correctOption2)
                .append(", correctOption3 = ").append(correctOption3)
                .append(", questionType = ").append(questionType)
                .append(", theme = ").append(theme)
                .append("}")
                .toString();
    }

}
