package model.entity;

import model.entity.entityenum.TestStatus;

import java.time.LocalDate;

public class TestInfo {
    private String userName;
    private String userLastName;
    private String userLogin;
    private Double userRank;
    private String theme;
    private Integer userPoints;
    private Integer maxPoints;
    private Double rightAnswersPercent;
    private LocalDate date;
    private TestStatus testStatus;

    private TestInfo(Builder builder) {
        this.userName = builder.userName;
        this.userLastName = builder.userLastName;
        this.userLogin = builder.userLogin;
        this.userRank = builder.userRank;
        this.theme = builder.theme;
        this.userPoints = builder.userPoints;
        this.maxPoints = builder.maxPoints;
        this.date = builder.date;
        this.rightAnswersPercent = builder.rightAnswersPercent;
        this.testStatus = builder.testStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public Double getUserRank() {
        return userRank;
    }

    public String getTheme() {
        return theme;
    }

    public Integer getUserPoints() {
        return userPoints;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public Double getRightAnswersPercent() {
        return rightAnswersPercent;
    }

    public LocalDate getDate() {
        return date;
    }

    public TestStatus getTestStatus() {
        return testStatus;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "userName='" + userName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userRank=" + userRank +
                ", theme='" + theme + '\'' +
                ", userPoints=" + userPoints +
                ", maxPoints=" + maxPoints +
                ", rightAnswersPercent=" + rightAnswersPercent +
                ", date=" + date +
                ", testStatus=" + testStatus +
                '}';
    }

    public String getUserLogin() {
        return userLogin;
    }

    public static class Builder {
        private String userName;
        private String userLastName;
        private String userLogin;
        private Double userRank;
        private String theme;
        private Integer userPoints;
        private Integer maxPoints;
        private Double rightAnswersPercent;
        private LocalDate date;
        private TestStatus testStatus;

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withUserLastName(String userLastName) {
            this.userLastName = userLastName;
            return this;
        }

        public Builder withUserLogin(String userLogin) {
            this.userLogin = userLogin;
            return this;
        }

        public Builder withUserRank(Double userRank) {
            this.userRank = userRank;
            return this;
        }

        public Builder withTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public Builder withUserPoints(Integer userPoints) {
            this.userPoints = userPoints;
            return this;
        }

        public Builder withMaxPoints(Integer maxPoints) {
            this.maxPoints = maxPoints;
            return this;
        }

        public Builder withPercentOfRightAnswers(Double rightAnswersPercent) {
            this.rightAnswersPercent = rightAnswersPercent;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public TestInfo build() {
            return new TestInfo(this);
        }


    }

}
