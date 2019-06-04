package model.service.factory;

import model.service.*;
import model.service.impl.*;

public class ServiceFactory {

    private static volatile ServiceFactory serviceFactory ;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        if (serviceFactory == null){
            synchronized (ServiceFactory.class){
                if (serviceFactory == null){
                    serviceFactory = new ServiceFactory();
                }
            }
        }
        return serviceFactory;
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
