package model.entity;

import model.entity.entityenum.QuestionType;

public class Question {
    //в сервисах возвращать опшонсы. Переписать ошибки, чтоб успокоить юзера. Подумать над таблицами в бд. Разбить валидацию на несколько классов
    //Статику не использовать валидатор не статика.
    //в ресурсы - ресорбандл и бд, логгер. для месседжей переводы не надо
    //фейковая БД h2 + хорошо интеграционные тесты
    //таблица роли, подумать над разными айдишниками
    //подуамть над логин - имейл
    // локалдейт дата, в базе дейт
    //юзер регистрируется паролем и имейлом. а потм может заполнить профиль
    //табличка роль - айди, тип
    private Long questionId;
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

    public Long getQuestionId() {
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

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", percentOfRightAnswers=" + percentOfRightAnswers +
                ", incorrectOption1='" + incorrectOption1 + '\'' +
                ", incorrectOption2='" + incorrectOption2 + '\'' +
                ", incorrectOption3='" + incorrectOption3 + '\'' +
                ", correctOption1='" + correctOption1 + '\'' +
                ", correctOption2='" + correctOption2 + '\'' +
                ", correctOption3='" + correctOption3 + '\'' +
                ", questionType=" + questionType +
                ", theme=" + theme +
                '}';
    }

    public static class Builder {
        private Long id;
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

        public Builder withId(Long id) {
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

}
