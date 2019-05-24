package model.entity;

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
    private Integer id;
    private Integer courseId;
    private Integer points;
    private Double percentOfRightAnswers;
    private String question;
    private String incorrectOption1;
    private String incorrectOption2;
    private String incorrectOption3;
    private String correctOption;

    private Question(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.points = builder.points;
        this.percentOfRightAnswers = builder.percentOfRightAnswers;
        this.question = builder.question;
        this.incorrectOption1 = builder.incorrectOption1;
        this.incorrectOption2 = builder.incorrectOption2;
        this.incorrectOption3 = builder.incorrectOption3;
        this.correctOption = builder.correctOption;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Double getPercentOfRightAnswers() {
        return percentOfRightAnswers;
    }

    public String getQuestion() {
        return question;
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

    public String getCorrectOption() {
        return correctOption;
    }

    public void setPercentOfRightAnswers(Double percentOfRightAnswers) {
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    public Integer getPoints() {
        return points;
    }

    public static class Builder {
        private Integer id;
        private Integer courseId;
        private Integer points;
        private Double percentOfRightAnswers;
        private String question;
        private String incorrectOption1;
        private String incorrectOption2;
        private String incorrectOption3;
        private String correctOption;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder setPoints(Integer points) {
            this.points = points;
            return this;
        }

        public Builder setPercentOfRightAnswers(Double percentOfRightAnswers) {
            this.percentOfRightAnswers = percentOfRightAnswers;
            return this;
        }

        public Builder setInCorrectOption1(String incorrectOption1) {
            this.incorrectOption1 = incorrectOption1;
            return this;
        }

        public Builder setInCorrectOption2(String incorrectOption2) {
            this.incorrectOption2 = incorrectOption2;
            return this;
        }

        public Builder setInCorrectOption3(String incorrectOption3) {
            this.incorrectOption3 = incorrectOption3;
            return this;
        }

        public Builder setCorrectOption(String correctOption) {
            this.correctOption = correctOption;
            return this;
        }
        public Question build() {
            return new Question(this);
        }

    }

}
