package model.entity;

public class Course {
    private Integer id;
    private String courseName;
    private Integer amountOfQuestions;
    private Long usersPassed;

    private Course(Builder builder) {
        this.id = builder.id;
        this.courseName = builder.courseName;
        this.amountOfQuestions = builder.amountOfQuestions;
        this.usersPassed = builder.usersPassed;
    }

    public Integer getId() {
        return id;
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

    public void setUsersPassed(Long usersPassed) {
        this.usersPassed = usersPassed;
    }

    public static class Builder {
        private Integer id;
        private String courseName;
        private Integer amountOfQuestions;
        private Long usersPassed;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder setAmountOfQuestions(Integer amountOfQuestions) {
            this.amountOfQuestions = amountOfQuestions;
            return this;
        }

        public Builder setUsersPassed(Long usersPassed) {
            this.usersPassed = usersPassed;
            return this;
        }

        public Course build() {
            return new Course(this);
        }

    }
}
