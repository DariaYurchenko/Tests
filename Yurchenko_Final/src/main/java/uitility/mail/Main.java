package uitility.mail;

import model.dao.connector.Connector;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.*;
import model.service.TestService;
import model.service.ThemeService;
import model.service.impl.QuestionServiceImpl;
import model.service.impl.TestServiceImpl;
import model.service.impl.ThemeServiceImpl;
import uitility.pagination.Pagination;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        String NAME_OR_LASTNAME = "^[a-zA-Zа-яА-ЯёЁ]{1,30}$";
        User user = new User.Builder().withId(168L).build();
        TestService testService = new TestServiceImpl();
        List<Test> tests = testService.findTestsByParameter("test_user_id", user.getUserId());

        /*ThemeService themeServiceImpl = new ThemeServiceImpl();
        List<Theme> list = themeServiceImpl.findAll();
        int rows = list.size();
        //System.out.println(list);
        int recordsPerPage = 5;
        int currentPage = 2;
        Pagination pagination = new Pagination(recordsPerPage, currentPage);
        List<Theme> themes = themeServiceImpl.findThemesForPagination(pagination.calculateStart(pagination.calculateNumOfPages(rows)), recordsPerPage);
        System.out.println(themes);
        System.out.println(pagination.calculateNumOfPages(rows));*/


    }





    }

