package model.entity;

import model.entity.entityenum.TestStatus;

import java.time.LocalDate;

public class Test {
    private Integer id;
    private Long userId;
    private Integer courseId;
    private Double grade;

    //TODO: Maybe should change to Date + Locale
    private LocalDate date;
    private TestStatus testStatus;

    public Test(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.courseId = builder.courseId;
        this.grade = builder.grade;
        this.date = builder.date;
        this.testStatus = builder.testStatus;
    }

    public Integer getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public TestStatus getTestStatus() {
        return testStatus;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public static class Builder {
        private Integer id;
        private Long userId;
        private Integer courseId;
        private double grade;
        private LocalDate date;
        private TestStatus testStatus;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setCourseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder setGrade(Double grade) {
            this.grade = grade;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setTestStatus(TestStatus testStatus) {
            this.testStatus = testStatus;
            return this;
        }

        public Test build() {
            return new Test(this);
        }
    }
}
