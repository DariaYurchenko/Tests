package model.dao.impl;

import exception.DaoException;
import model.dao.ThemeDao;
import model.dao.connector.Connector;
import model.entity.Theme;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThemeDaoImpl extends GenericDaoImpl<Theme> implements ThemeDao {
    private static final Logger LOGGER = Logger.getLogger(ThemeDaoImpl.class);

    public ThemeDaoImpl(Connector connector) {
        super(connector);
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
            throw new DaoException(e);
        }
    }

    /*@Override
    public List<Theme> findThemesForPagination(int startRecord, int recordsPerPage) {
        List<Theme> themes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(FIND_THEMES_FOR_PAGINATION)) {
            preparedStatement.setInt(1, startRecord);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                themes.add(new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME)));
            }
            return themes;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding themes for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }*/

    @Override
    public List<Theme> parseResultSet(ResultSet rs) {
        List<Theme> themes = new ArrayList<>();
        try {
            while(rs.next()) {
                themes.add(new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME)));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of theme: " + e.getMessage());
            throw new DaoException(e);
        }
        return themes;
    }

    @Override
    public Optional<Theme> parseResultSetToFindById(ResultSet rs) {
        try {
            return Optional.of(new Theme(rs.getLong(THEME_ID), rs.getString(THEME_NAME)));
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of theme while finding by id: " + e.getMessage());
            throw new DaoException(e);
        }
    }

}
