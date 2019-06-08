package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Question;
import model.service.AnswerService;
import model.service.QuestionService;
import model.service.impl.AnswerServiceImpl;
import model.service.impl.QuestionServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Called after answering the question during test.
 * Shows right answers emphasizing on the user's one.
 */
public class ShowRightAnswers extends Command implements CommandPages {
    private static final String RADIO = "Radio";
    private static final String CHECKBOX = "Checkbox";
    private static final String TEXT = "Text";

    private QuestionService questionService;
    private AnswerService answerService;

    public ShowRightAnswers() {
        this.questionService = new QuestionServiceImpl();
        this.answerService = new AnswerServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Question question = (Question) req.getSession().getAttribute("question");
        int questionPoints = questionService.setQuestionPoints(question);

        if (ifSingleChoiceQuestion(question)) {
            String userAnswer = req.getParameter("singleChoice");

            createSingleAnswerAndAddItToList(req, question, userAnswer, questionPoints);

            updatePointsForSingleChoiceQuestion(question, userAnswer);

            req.getSession().setAttribute("userAnswer", userAnswer);
        }
        if (ifMultipleChoiceQuestion(question)) {
            String[] correctAnswers = makeListOfCorrectAnswers(question);
            String[] userAnswers = req.getParameterValues("multipleChoice");

            /**
             * Stub if user hasn't chosen anything
             * in checkbox
             */
            if (userAnswers == null) {
                userAnswers = new String[]{"NULL"};
            }

            createMultipleAnswerAndAddItToList(req, userAnswers, correctAnswers, questionPoints);

            updatePointsForMultipleChoiceQuestion(question, userAnswers, correctAnswers);

            sendUserAnswersRequest(req, userAnswers);

        }

        calculateQuestionRightAnswersPercent(req, question);

        int counter = Integer.parseInt(req.getParameter("counter"));

        req.getSession().setAttribute("forward", "SHOW_ANSWERS");
        req.getSession().setAttribute("counter", counter + 1);

        return CommandResult.forward(PASS_TESTS);
    }

    private String getQuestionType(Question question) {
        return question.getQuestionType().getType();
    }

    private void updatePointsForSingleChoiceQuestion(Question question, String userAnswer) {
        if (userAnswer.equals(question.getCorrectOption1())) {
            questionService.setAnswers(question.getQuestionId(), 1, 1);
        } else {
            questionService.setAnswers(question.getQuestionId(), 0, 1);
        }
    }

    private void updatePointsForMultipleChoiceQuestion(Question question, String[] userAnswers, String[] correctAnswers) {
        Arrays.sort(userAnswers);
        Arrays.sort(correctAnswers);
        if (Arrays.equals(userAnswers, correctAnswers)) {
            questionService.setAnswers(question.getQuestionId(), 1, 1);
        } else {
            questionService.setAnswers(question.getQuestionId(), 0, 1);
        }
    }

    private void calculateQuestionRightAnswersPercent(HttpServletRequest req, Question question) {
        double answerPercent = question.getPercentOfRightAnswers();
        req.getSession().setAttribute("answerPercent", answerPercent);
    }

    private void createSingleAnswerAndAddItToList(HttpServletRequest req, Question question, String userAnswer, int questionPoints) {
        Answer answer = createSingleAnswer(questionPoints, userAnswer, question);
        addUserAnswerToList(req, answer);
    }

    private void createMultipleAnswerAndAddItToList(HttpServletRequest req, String[] userAnswers, String[] correctAnswers, int questionPoints) {
        Answer answer = createMultipleAnswer(questionPoints, userAnswers, correctAnswers);
        addUserAnswerToList(req, answer);
    }

    private void addUserAnswerToList(HttpServletRequest req, Answer answer) {
        List<Answer> userAnswers = (ArrayList) req.getSession().getAttribute("userAnswers");
        userAnswers.add(answer);
        req.getSession().setAttribute("userAnswers", userAnswers);
    }

    private Answer createSingleAnswer(int questionPoints, String userAnswer, Question question) {
        return answerService.makeSingleChoiceAnswer(questionPoints, userAnswer, question.getCorrectOption1());
    }

    private Answer createMultipleAnswer(int questionPoints, String[] userAnswers, String[] correctAnswers) {
        return answerService.makeMultipleChoiceAnswer(questionPoints, userAnswers, correctAnswers);
    }

    private String[] makeListOfCorrectAnswers(Question question) {
        /**
         * 1 correct answer
         */
        if(question.getCorrectOption2() == null) {
            return new String[]{question.getCorrectOption1()};
        }
        /**
         * 2 correct answers
         */
        if (question.getCorrectOption3() == null) {
            return new String[]{question.getCorrectOption1(), question.getCorrectOption2()};
        }
        /**
         * 3 correct answers
         */
        else {
            return new String[]{question.getCorrectOption1(), question.getCorrectOption2(), question.getCorrectOption3()};
        }
    }

    private void sendUserAnswersRequest(HttpServletRequest req, String[] userAnswers) {
        if (userAnswers.length == 1) {
            req.getSession().setAttribute("userAnswer1", userAnswers[0]);
        }
        if (userAnswers.length == 2) {
            req.getSession().setAttribute("userAnswer1", userAnswers[0]);
            req.getSession().setAttribute("userAnswer2", userAnswers[1]);
        }
        if (userAnswers.length == 3) {
            req.getSession().setAttribute("userAnswer1", userAnswers[0]);
            req.getSession().setAttribute("userAnswer2", userAnswers[1]);
            req.getSession().setAttribute("userAnswer3", userAnswers[2]);
        }
        if (userAnswers.length == 4) {
            req.getSession().setAttribute("userAnswer1", userAnswers[0]);
            req.getSession().setAttribute("userAnswer2", userAnswers[1]);
            req.getSession().setAttribute("userAnswer3", userAnswers[2]);
            req.getSession().setAttribute("userAnswer4", userAnswers[3]);
        }
    }

    private boolean ifSingleChoiceQuestion(Question question) {
        return RADIO.equalsIgnoreCase(getQuestionType(question)) ||
                TEXT.equalsIgnoreCase(getQuestionType(question));
    }

    private boolean ifMultipleChoiceQuestion(Question question) {
        return CHECKBOX.equalsIgnoreCase(getQuestionType(question));
    }
}
