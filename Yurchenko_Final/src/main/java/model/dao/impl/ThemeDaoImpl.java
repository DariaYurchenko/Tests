package model.dao.impl;

import exception.DaoRuntimeException;
import model.dao.ThemeDao;
import model.entity.Theme;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeDaoImpl extends GenericDaoImpl<Theme> implements ThemeDao {
    private static final Logger LOGGER = Logger.getLogger(ThemeDaoImpl.class);

    private static final String INSERT_THEME = "INSERT INTO themes(name, amountOfQuestions) VALUES(?, ?);";
    private static final String DELETE_THEME = "DELETE FROM themes WHERE theme_id = ?;";
    private static final String DELETE_ALL_THEMES = "DELETE FROM themes;";
    private static final String SELECT_ALL_THEMES = "SELECT * FROM themes;";
    private static final String FIND_THEME_BY_ID = "SELECT * FROM themes WHERE theme_id = ?;";
    private static final String FIND_THEME_BY_PARAMETER = "SELECT * FROM themes WHERE %s = ?;";
    private static final String FIND_THEMES_FOR_PAGINATION = "SELECT * FROM themes LIMIT ?, ?;";
    private static final String UPDATE_THEME = "UPDATE themes SET %s = ? WHERE theme_id = ?;";

    private static final String THEME_NAME = "theme_name";
    private static final String THEME_ID = "theme_id";

    public ThemeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String createQueryToSave() {
        return INSERT_THEME;
    }

    @Override
    public String createQueryToFindById() {
        return FIND_THEME_BY_ID;
    }

    @Override
    public String createQueryToDelete() {
        return DELETE_THEME;
    }

    @Override
    public String createQueryToDeleteAll() {
        return DELETE_ALL_THEMES;
    }

    @Override
    public String createQueryToFindAll() {
        return SELECT_ALL_THEMES;
    }

    @Override
    public String createQueryToPagination() {
        return FIND_THEMES_FOR_PAGINATION;
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_THEME_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        return String.format(UPDATE_THEME, column);
    }

    @Override
    public void prepareStatementToSave(PreparedStatement ps, Theme theme) {
        try {
            ps.setString(1, theme.getThemeName());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for adding theme: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public List<Theme> parseResultSet(ResultSet rs) {
        List<Theme> themes = new ArrayList<>();
        try {
            while(rs.next()) {
                themes.add(new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME)));
            }
            return themes;
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of theme: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public Theme parseResultSetToFindById(ResultSet rs) {
        try {
            return new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME));
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of theme while finding by id: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

}
