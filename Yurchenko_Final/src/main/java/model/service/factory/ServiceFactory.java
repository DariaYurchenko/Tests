package model.service.factory;

import model.service.UserService;
import model.service.QuestionService;
import model.service.TestService;
import model.service.TestInfoService;
import model.service.ThemeService;
import model.service.AnswerService;
import model.service.impl.UserServiceImpl;
import model.service.impl.QuestionServiceImpl;
import model.service.impl.ThemeServiceImpl;
import model.service.impl.TestServiceImpl;
import model.service.impl.TestInfoServiceImpl;
import model.service.impl.AnswerServiceImpl;

public class ServiceFactory {

    private static volatile ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        ServiceFactory serviceInstance = instance;
        if (serviceInstance == null) {
            synchronized (ServiceFactory.class) {
                serviceInstance = instance;
                if (instance == null) {
                    serviceInstance =  new ServiceFactory();
                    instance = serviceInstance;
                }
            }
        }
        return serviceInstance;
    }

    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public QuestionService getQuestionService() {
        return new QuestionServiceImpl();
    }

    public TestInfoService getTestInfoService() {
        return new TestInfoServiceImpl();
    }

   public TestService getTestService() {
        return new TestServiceImpl();
    }

    public ThemeService getThemeService() {
        return new ThemeServiceImpl();
    }

    public AnswerService getAnswerService() {
        return new AnswerServiceImpl();
    }

}
