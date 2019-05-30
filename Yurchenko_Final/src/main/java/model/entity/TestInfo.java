package model.entity;

import model.entity.status.TestStatus;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestInfo testInfo = (TestInfo) o;
        return Objects.equals(userName, testInfo.userName) &&
                Objects.equals(userLastName, testInfo.userLastName) &&
                Objects.equals(userLogin, testInfo.userLogin) &&
                Objects.equals(userRank, testInfo.userRank) &&
                Objects.equals(theme, testInfo.theme) &&
                Objects.equals(userPoints, testInfo.userPoints) &&
                Objects.equals(maxPoints, testInfo.maxPoints) &&
                Objects.equals(rightAnswersPercent, testInfo.rightAnswersPercent) &&
                Objects.equals(date, testInfo.date) &&
                testStatus == testInfo.testStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userLastName, userLogin, userRank, theme, userPoints, maxPoints,
                rightAnswersPercent, date, testStatus);
    }

}
