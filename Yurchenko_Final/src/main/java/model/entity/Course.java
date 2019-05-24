package model.entity;

public class Course {
    private Long courseId;
    private String courseName;
    private Integer amountOfQuestions;
    private Long usersPassed;

    private Course(Builder builder) {
        this.courseId = builder.id;
        this.courseName = builder.courseName;
        this.amountOfQuestions = builder.amountOfQuestions;
        this.usersPassed = builder.usersPassed;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getAmountOfQuestions() {
        return amountOfQuestions;
    }

    public Long getUsersPassed() {
        return usersPassed;
    }

    public static class Builder {
        private Long id;
        private String courseName;
        private Integer amountOfQuestions;
        private Long usersPassed;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder withAmountOfQuestions(Integer amountOfQuestions) {
            this.amountOfQuestions = amountOfQuestions;
            return this;
        }

        public Builder withUsersPassed(Long usersPassed) {
            this.usersPassed = usersPassed;
            return this;
        }

        public Course build() {
            return new Course(this);
        }

    }
}
