package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Question;
import model.service.impl.AnswerServiceImpl;
import model.service.impl.QuestionServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

public class PassTest extends Command implements CommandPages {
    private QuestionServiceImpl questionServiceImpl;
    private AnswerServiceImpl answerServiceImpl;

    public PassTest() {
        this.questionServiceImpl = new QuestionServiceImpl();
        this.answerServiceImpl = new AnswerServiceImpl();
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Question> questions = (ArrayList) req.getSession().getAttribute("questions");
        req.getSession().setAttribute("length", questions.size());

        int counter = Integer.parseInt(req.getParameter("counter"));
        req.setAttribute("progress", countProgressOfTest(req, questions, counter));

        if(req.getParameter("forward") == null || req.getParameter("forward").equals("FALSE")) {
            Question question = questions.get(counter);
            List<String> options = makeListOfOptions(question);
            Collections.shuffle(options);

            req.getSession().setAttribute("options", options);
            req.getSession().setAttribute("question", question);
            req.getSession().setAttribute("length", questions.size());
            req.getSession().setAttribute("forward", "TRUE");
        }
        else {
            Question question = (Question) req.getSession().getAttribute("question");
            int questionPoints = questionServiceImpl.setQuestionPoints(question);

            if(getQuestionType(question).equalsIgnoreCase("Radio") ||
                    getQuestionType(question).equalsIgnoreCase("Text")) {
                String userAnswer = req.getParameter("radio");

                createSingleAnswerAndAddItToList(req, question, userAnswer, questionPoints);

                setPointsForSingleChoiceQuestion(question, userAnswer);

                req.setAttribute("userAnswer", userAnswer);
            }

            if(getQuestionType(question).equalsIgnoreCase("checkbox")) {
                String[] correctAnswers  = makeListOfCorrectAnswers(question);
                String[] userAnswers = req.getParameterValues("checkbox");

                /*if(userAnswers  == null) {
                    userAnswers = new String[] {"NULL"};
                }*/

                createMultipleAnswerAndAddItToList(req, userAnswers, correctAnswers, questionPoints);

                setPointsForMultipleChoiceQuestion(question, userAnswers, correctAnswers);

                sendUserAnswersRequest(req, userAnswers);

                sendCounterForwardRequest(req);
                }
            changeQuestionRightAnswersPercent(req, question);
            sendCounterForwardRequest(req);
            }

        return CommandResult.forward(PASS_TESTS);
    }

    private List<String> makeListOfOptions(Question question) {
        if(getQuestionType(question).equalsIgnoreCase("Radio")){
            return new ArrayList<>(Arrays.asList(question.getIncorrectOption1(),
                    question.getIncorrectOption2(), question.getIncorrectOption3(), question.getCorrectOption1()));
        }
        if(getQuestionType(question).equalsIgnoreCase("Checkbox")) {
            if (question.getCorrectOption3() == null) {
                return Arrays.asList(question.getIncorrectOption1(),
                        question.getIncorrectOption2(), question.getCorrectOption2(), question.getCorrectOption1());
            }
            return Arrays.asList(question.getIncorrectOption1(),
                    question.getCorrectOption3(), question.getCorrectOption2(), question.getCorrectOption1());
        }
        else {
            return singletonList(question.getCorrectOption1());
        }
    }

    private String getQuestionType(Question question) {
        return question.getQuestionType().getType();
    }

    private void setPointsForSingleChoiceQuestion(Question question, String userAnswer) {
        if(userAnswer.equals(question.getCorrectOption1())) {
            questionServiceImpl.setAnswers(question.getQuestionId(), 1, 1);
        }
        else {
            questionServiceImpl.setAnswers(question.getQuestionId(), 0, 1);
        }
    }

    private void setPointsForMultipleChoiceQuestion(Question question, String[] userAnswers, String[] correctAnswers) {
        if (Arrays.equals(userAnswers, correctAnswers)) {
            questionServiceImpl.setAnswers(question.getQuestionId(), 1, 1);
        } else {
            questionServiceImpl.setAnswers(question.getQuestionId(), 0, 1);
        }
    }

    private void changeQuestionRightAnswersPercent(HttpServletRequest req, Question question) {
        String answerPercent = String.valueOf(questionServiceImpl.findCurrentAnswers(question.getQuestionId()));
        req.setAttribute("answerPercent", answerPercent);
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
        return answerServiceImpl.makeAnswer(questionPoints, userAnswer, question.getCorrectOption1());
    }

    private Answer createMultipleAnswer(int questionPoints, String[] userAnswers, String[] correctAnswers) {
        return answerServiceImpl.makeMultipleChoiceAnswer(questionPoints, userAnswers, correctAnswers);
    }

    private String[] makeListOfCorrectAnswers(Question question) {
        if(question.getCorrectOption3() != null) {
            return new String[] {question.getCorrectOption1(), question.getCorrectOption2(), question.getCorrectOption3(), };
        }
        else {
            return new String[] {question.getCorrectOption1(), question.getCorrectOption2()};
        }
    }

    private void sendUserAnswersRequest(HttpServletRequest req, String[] userAnswers) {
        if(userAnswers.length == 1) {
            req.setAttribute("userAnswer1", userAnswers[0]);
        }
        if (userAnswers.length == 2) {
            req.setAttribute("userAnswer1", userAnswers[0]);
            req.setAttribute("userAnswer2", userAnswers[1]);
        }
        if(userAnswers.length == 3) {
            req.setAttribute("userAnswer1", userAnswers[0]);
            req.setAttribute("userAnswer2", userAnswers[1]);
            req.setAttribute("userAnswer3", userAnswers[2]);
        }
        if(userAnswers.length == 4){
            req.setAttribute("userAnswer1", userAnswers[0]);
            req.setAttribute("userAnswer2", userAnswers[1]);
            req.setAttribute("userAnswer3", userAnswers[2]);
            req.setAttribute("userAnswer4", userAnswers[3]);
        }
    }

    private void sendCounterForwardRequest(HttpServletRequest req) {
        int counter = Integer.parseInt(req.getParameter("counter"));
        req.setAttribute("forward", "FALSE");
        req.getSession().setAttribute("counter", counter + 1);
    }
     private String countProgressOfTest(HttpServletRequest req, List<Question> questions, int counter) {
        return String.valueOf(Math.round(counter * 100.0/questions.size()));
     }

}