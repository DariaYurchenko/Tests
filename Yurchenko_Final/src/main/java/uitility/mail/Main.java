package uitility.mail;

import model.dao.connector.Connector;
import model.dao.impl.TestInfoDaoImpl;
import model.entity.Question;
import model.entity.TestInfo;
import model.entity.Theme;
import model.service.ThemeService;
import model.service.impl.QuestionServiceImpl;
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
        //System.out.println("Рома".matches(NAME_OR_LASTNAME));

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

