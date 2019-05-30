package model.dao;

import model.entity.Theme;

import java.util.List;

public interface ThemeDao extends GenericDao<Theme> {
    String INSERT_THEME = "INSERT INTO themes(name, amountOfQuestions) VALUES(?, ?);";
    String DELETE_THEME = "DELETE FROM themes WHERE theme_id = ?;";
    String DELETE_ALL_THEMES = "DELETE FROM themes;";
    String SELECT_ALL_THEMES = "SELECT * FROM themes;";
    String FIND_THEME_BY_ID = "SELECT * FROM themes WHERE theme_id = ?;";
    String FIND_THEME_BY_PARAMETER = "SELECT * FROM themes WHERE %s = ?;";
    String FIND_THEMES_FOR_PAGINATION = "SELECT * FROM themes LIMIT ?, ?;";
    String UPDATE_THEME = "UPDATE themes SET %s = ? WHERE theme_id = ?;";

    String THEME_NAME = "theme_name";
    String THEME_ID = "theme_id";

    //List<Theme> findThemesForPagination(int startRecord, int recordsPerPage);


}
