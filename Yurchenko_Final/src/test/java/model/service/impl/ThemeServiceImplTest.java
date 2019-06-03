package model.service.impl;

import model.dao.ThemeDao;
import model.entity.Theme;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ThemeServiceImplTest {

    @Mock
    ThemeDao themeDao;
    @InjectMocks
    ThemeServiceImpl themeService;

    @Test
    public void shouldFindAllThemes() {
        when(themeDao.findAll()).thenReturn(Arrays.asList(new Theme("theme1"), new Theme("theme2")));
        List<Theme> themes = themeService.findAll();
        assertNotNull(themes);
    }

    @Test
    public void shouldAddTheme() {
        Mockito.doNothing().when(themeDao).add(new Theme("theme1"));
        themeService.addTheme(new Theme("theme1"));
        verify(themeDao).add(new Theme("theme1"));
    }

    @Test
    public void shouldDeleteById() {
        Long themeId = 1L;
        Mockito.doNothing().when(themeDao).deleteById(themeId);
        themeService.deleteThemeById(themeId);
        verify(themeDao).deleteById(themeId);
    }

    @Test
    public void shouldDeleteAll() {
        Mockito.doNothing().when(themeDao).deleteAll();
        themeService.deleteAll();
        verify(themeDao).deleteAll();
    }

    @Test
    public void shouldFindThemesByParameter() {
        String column = "name";
        String themeName = "theme1";
        when(themeDao.findByParameter(column, themeName)).thenReturn(Arrays.asList(new Theme("theme1"), new Theme("theme2")));
        List<Theme> themes = themeService.findThemeByParameter(column, themeName);
        assertNotNull(themes);
    }

    @Test
    public void shouldUpdateTheme() {
        String column = "name";
        String themeName = "theme1";
        Long themeId = 1L;
        Mockito.doNothing().when(themeDao).update(column, themeName, themeId);
        themeService.updateTheme(column, themeName, themeId);
        verify(themeDao).update(column, themeName, themeId);
    }

    @Test
    public void shouldFindThemeById() {
        Long themeId = 1L;
        when(themeDao.findById(themeId)).thenReturn(Optional.of(new Theme("theme1")));
        Theme actual = themeService.findThemeById(themeId);
        assertNotNull(actual);
    }

    @Test
    public void shouldFindThemesForPagination() {
        int recordsPerPage = 5;
        int startRecords = 1;

        when(themeDao.findForPagination(startRecords, recordsPerPage)).thenReturn(Arrays.asList(new Theme("theme1"), new Theme("theme2")));
        List<Theme> themes = themeService.findThemesForPagination(startRecords, recordsPerPage);
        assertNotNull(themes);
    }

}
