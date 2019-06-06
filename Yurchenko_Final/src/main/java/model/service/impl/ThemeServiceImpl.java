package model.service.impl;

import model.dao.ThemeDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.entity.Theme;
import model.service.ThemeService;
import java.util.List;
import java.util.Optional;

public class ThemeServiceImpl implements ThemeService {
    private ThemeDao themeDao;

    public ThemeServiceImpl() {
        this.themeDao =  DaoFactory.getDAOFactory(DbNames.MYSQL).getThemeDao();
    }

    @Override
    public void addTheme(Theme theme) {
        themeDao.add(theme);
    }

    @Override
    public void updateTheme(String column, Object value, Long themeId) {
        themeDao.update(column, value, themeId);
    }

    @Override
    public List<Theme> findAll() {
        return themeDao.findAll();
    }

    @Override
    public List<Theme> findThemesForPagination(int startRecord, int recordsPerPage) {
        return themeDao.findForPagination(startRecord, recordsPerPage);
    }

    @Override
    public Optional<Theme> findThemeById(Long themeId) {
        return themeDao.findById(themeId);
    }

    @Override
    public List<Theme> findThemeByParameter(String column, Object value) {
        return themeDao.findByParameter(column, value);
    }

    @Override
    public void deleteAllThemes() {
       themeDao.deleteAll();
    }

    @Override
    public void deleteThemeById(Long themeId) {
        themeDao.deleteById(themeId);
    }

}
