package model.entity;

import model.entity.status.TestStatus;
import java.time.LocalDate;
import java.util.Objects;

public class Test {
    private Long testId;
    private Long userId;
    private Long themeId;
    private Integer userPoints;
    private Integer maxPoints;
    private Double rightAnswersPercent;
    private LocalDate date;
    private TestStatus testStatus;

    public Test(Builder builder) {
        this.testId = builder.testId;
        this.userId = builder.userId;
        this.themeId = builder.themeId;
        this.userPoints = builder.userPoints;
        this.maxPoints = builder.maxPoints;
        this.rightAnswersPercent = builder.rightAnswersPercent;
        this.date = builder.date;
        this.testStatus = builder.testStatus;
    }

    public Long getTestId() {
        return testId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getThemeId() {
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

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public static class Builder {
        private Long testId;
        private Long userId;
        private Long themeId;
        private Integer userPoints;
        private Integer maxPoints;
        private double rightAnswersPercent;
        private LocalDate date;
        private TestStatus testStatus;

        public Builder withId(Long testId) {
            this.testId = testId;
            return this;
        }

        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withThemeId(Long themeId) {
            this.themeId = themeId;
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
        Test test = (Test) o;
        return Objects.equals(testId, test.testId) &&
                Objects.equals(userId, test.userId) &&
                Objects.equals(themeId, test.themeId) &&
                Objects.equals(userPoints, test.userPoints) &&
                Objects.equals(maxPoints, test.maxPoints) &&
                Objects.equals(rightAnswersPercent, test.rightAnswersPercent) &&
                Objects.equals(date, test.date) &&
                testStatus == test.testStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, userId, themeId, userPoints, maxPoints, rightAnswersPercent, date, testStatus);
    }

}
