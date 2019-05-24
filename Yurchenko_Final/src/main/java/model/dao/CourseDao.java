package model.dao;

import model.entity.Course;

public interface CourseDao extends GenericDao<Course> {
    static final String INSERT_COURSE = "INSERT INTO courses(name, amountOfQuestions) VALUES(?, ?);";
    static final String DELETE_COURSE = "DELETE FROM courses WHERE course_id = ?;";
    static final String DELETE_ALL_COURSES = "DELETE FROM courses;";
    static final String SELECT_ALL_COURSES = "SELECT * FROM courses;";
    static final String FIND_COURSE_BY_ID = "SELECT * FROM courses WHERE course_id = ?;";
    static final String FIND_COURSE_BY_PARAMETER = "SELECT * FROM courses WHERE %s = ?;";
    static final String UPDATE_COURSE = "UPDATE courses SET %s = ? WHERE course_id = ?;";


    static final String COURSE_NAME = "course_name";
    static final String COURSE_ID = "course_id";
    static final String AMOUNT_OF_QUESTIONS = "amounts_of_questions";
    static final String USERS_PASSED = "users_passed";


}
