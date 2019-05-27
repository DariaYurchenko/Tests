package model.dao;

import model.entity.Theme;

import java.util.List;

public interface ThemeDao extends GenericDao<Theme> {
    static final String INSERT_THEME = "INSERT INTO themes(name, amountOfQuestions) VALUES(?, ?);";
    static final String DELETE_THEME = "DELETE FROM themes WHERE theme_id = ?;";
    static final String DELETE_ALL_THEMES = "DELETE FROM themes;";
    static final String SELECT_ALL_THEMES = "SELECT * FROM themes;";
    static final String FIND_THEME_BY_ID = "SELECT * FROM themes WHERE theme_id = ?;";
    static final String FIND_THEME_BY_PARAMETER = "SELECT * FROM themes WHERE %s = ?;";
    static final String UPDATE_THEME = "UPDATE themes SET %s = ? WHERE theme_id = ?;";
    static final String SELECT_ALL_THEMES_FOR_PAGINATION = "SELECT * FROM themes LIMIT ?, ?;";


    static final String THEME_NAME = "theme_name";
    static final String THEME_ID = "theme_id";

    List<Theme> findThemesForPagination(int startRecord, int recordsPerPage);


}
