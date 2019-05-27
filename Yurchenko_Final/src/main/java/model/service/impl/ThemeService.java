package model.service.impl;

import model.dao.ThemeDao;
import model.dao.connector.Connector;
import model.dao.impl.ThemeDaoImpl;
import model.entity.Theme;

import java.util.List;

public class ThemeService {
    private ThemeDao themeDao = new ThemeDaoImpl(new Connector());

    public List<Theme> findAll() {
        return themeDao.findAll();
    }

    public List<Theme> findThemesForPagination(int startRecord, int recordsPerPage) {
        return themeDao.findThemesForPagination(startRecord, recordsPerPage);
    }
}
