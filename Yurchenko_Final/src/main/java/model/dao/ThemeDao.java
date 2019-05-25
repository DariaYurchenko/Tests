package model.dao;

import model.entity.Theme;

public interface ThemeDao extends GenericDao<Theme> {
    static final String INSERT_THEME = "INSERT INTO themes(name, amountOfQuestions) VALUES(?, ?);";
    static final String DELETE_THEME = "DELETE FROM themes WHERE theme_id = ?;";
    static final String DELETE_ALL_THEMES = "DELETE FROM themes;";
    static final String SELECT_ALL_THEMES = "SELECT * FROM themes;";
    static final String FIND_THEME_BY_ID = "SELECT * FROM themes WHERE theme_id = ?;";
    static final String FIND_THEME_BY_PARAMETER = "SELECT * FROM themes WHERE %s = ?;";
    static final String UPDATE_THEME = "UPDATE themes SET %s = ? WHERE theme_id = ?;";


    static final String COURSE_NAME = "course_name";
    static final String COURSE_ID = "course_id";
    static final String AMOUNT_OF_QUESTIONS = "amounts_of_questions";
    static final String USERS_PASSED = "users_passed";


}
