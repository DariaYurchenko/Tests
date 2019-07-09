package model.entity;

import model.entity.status.TestStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Test implements Serializable {
    private Integer testId;
    private Integer userId;
    private Integer themeId;
    private Integer userPoints;
    private Integer maxPossiblePoints;
    private Double rightAnswersPercent;
    private LocalDate date;
    private TestStatus testStatus;

    public Test(Builder builder) {
        this.testId = builder.testId;
        this.userId = builder.userId;
        this.themeId = builder.themeId;
        this.userPoints = builder.userPoints;
        this.maxPossiblePoints = builder.maxPossiblePoints;
        this.rightAnswersPercent = builder.rightAnswersPercent;
        this.date = builder.date;
        this.testStatus = builder.testStatus;
    }

    public Integer getTestId() {
        return testId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getThemeId() {
        return themeId;
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

    public Integer getUserPoints() {
        return userPoints;
    }

    public Integer getMaxPossiblePoints() {
        return maxPossiblePoints;
    }

    public static class Builder {
        private Integer testId;
        private Integer userId;
        private Integer themeId;
        private Integer userPoints;
        private Integer maxPossiblePoints;
        private double rightAnswersPercent;
        private LocalDate date;
        private TestStatus testStatus;

        public Builder withId(Integer testId) {
            this.testId = testId;
            return this;
        }

        public Builder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder withThemeId(Integer themeId) {
            this.themeId = themeId;
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

        public Builder withRightAnswersPercent(Double rightAnswersPercent) {
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

        public Test build() {
            return new Test(this);
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
        Test testToCompare = (Test) o;
        return Objects.equals(testId, testToCompare.testId) &&
                Objects.equals(userId, testToCompare.userId) &&
                Objects.equals(themeId, testToCompare.themeId) &&
                Objects.equals(userPoints, testToCompare.userPoints) &&
                Objects.equals(maxPossiblePoints, testToCompare.maxPossiblePoints) &&
                Objects.equals(rightAnswersPercent, testToCompare.rightAnswersPercent) &&
                Objects.equals(date, testToCompare.date) &&
                testStatus == testToCompare.testStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, userId, themeId, userPoints, maxPossiblePoints, rightAnswersPercent, date, testStatus);
    }

    @Override
    public String toString() {
        return new StringBuilder("{Test: ")
                .append("testId = ").append(testId)
                .append(", userId = ").append(userId)
                .append(",  themeId = ").append(themeId)
                .append(", userPoints = ").append(userPoints)
                .append(", maxPossiblePoints = ").append(maxPossiblePoints)
                .append(", rightAnswersPercent = ").append(rightAnswersPercent)
                .append(", date = ").append(date)
                .append(", testStatus = ").append(testStatus)
                .append("}")
                .toString();
    }

}
