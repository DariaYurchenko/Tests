package model.entity;

import model.entity.status.TestStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TestInfo implements Serializable {
    private String userName;
    private String userLastName;
    private String userLogin;
    /**
     * userRank - relation of user's
     * right answers to all his answers
     * in all tests
     */
    private Double userRank;
    private String theme;
    private Integer userPoints;
    private Integer maxPossiblePoints;
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
        this.maxPossiblePoints = builder.maxPossiblePoints;
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

    public Integer getMaxPossiblePoints() {
        return maxPossiblePoints;
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
        private Integer maxPossiblePoints;
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
            this.maxPossiblePoints = maxPoints;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestInfo testInfoToCompare = (TestInfo) o;
        return Objects.equals(userName, testInfoToCompare.userName) &&
                Objects.equals(userLastName, testInfoToCompare.userLastName) &&
                Objects.equals(userLogin, testInfoToCompare.userLogin) &&
                Objects.equals(userRank, testInfoToCompare.userRank) &&
                Objects.equals(theme, testInfoToCompare.theme) &&
                Objects.equals(userPoints, testInfoToCompare.userPoints) &&
                Objects.equals(maxPossiblePoints, testInfoToCompare.maxPossiblePoints) &&
                Objects.equals(rightAnswersPercent, testInfoToCompare.rightAnswersPercent) &&
                Objects.equals(date, testInfoToCompare.date) &&
                testStatus == testInfoToCompare.testStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userLastName, userLogin, userRank, theme, userPoints, maxPossiblePoints,
                rightAnswersPercent, date, testStatus);
    }

    @Override
    public String toString() {
        return new StringBuilder("{TestInfo: ")
                .append("userName = ").append(userName)
                .append(", userLastName = ").append(userLastName)
                .append(",  userLogin = ").append(userLogin)
                .append(", userRank = ").append(userRank)
                .append(", theme = ").append(theme)
                .append(", userPoints = ").append(userPoints)
                .append(", maxPossiblePoints = ").append(maxPossiblePoints)
                .append(", rightAnswersPercent = ").append(rightAnswersPercent)
                .append(", date = ").append(date)
                .append(", testStatus = ").append(testStatus)
                .append("}")
                .toString();
    }

}
