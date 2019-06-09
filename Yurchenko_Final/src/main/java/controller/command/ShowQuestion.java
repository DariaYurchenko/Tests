package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Question;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static java.util.Collections.singletonList;

/**
 * Cyclically called to show the next question during test.
 */

public class ShowQuestion extends Command implements CommandPages {
    private static final String RADIO = "Radio";
    private static final String CHECKBOX = "Checkbox";
    private static final String TEXT = "Text";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Question> questions = (ArrayList) req.getSession().getAttribute("questions");
        List<Question> rusQuestions = (ArrayList) req.getSession().getAttribute("rusQuestions");
        req.getSession().setAttribute("length", questions.size());

        int counter = Integer.parseInt(req.getParameter("counter"));

        req.getSession().setAttribute("progress", countProgressOfTest(questions, counter));

        Question question = questions.get(counter);
        Question rusQuestion = rusQuestions.get(counter);

        List<String> options = makeListOfOptions(question);
        Collections.shuffle(options);

        req.getSession().setAttribute("options", options);
        req.getSession().setAttribute("question", question);
        req.getSession().setAttribute("rusQuestion", rusQuestion);
        req.getSession().setAttribute("length", questions.size());
        req.getSession().setAttribute("forward", "SHOW_QUESTION");

        return CommandResult.forward(PASS_TESTS);
    }

    private String countProgressOfTest(List<Question> questions, int counter) {
        return String.valueOf(Math.round(counter * 100.0 / questions.size()));
    }

    private List<String> makeListOfOptions(Question question) {
        if (ifSingleChoiceQuestion(question)) {
            return Arrays.asList(question.getIncorrectOption1(),
                    question.getIncorrectOption2(), question.getIncorrectOption3(), question.getCorrectOption1());
        }
        if (ifMultipleChoiceQuestion(question)) {
            /**
             * 1 correct answer
             */
            if (question.getCorrectOption2() == null) {
                return Arrays.asList(question.getIncorrectOption1(),
                        question.getIncorrectOption2(), question.getIncorrectOption3(), question.getCorrectOption1());
            }
            /**
             * 2 correct answers
             */
            if (question.getCorrectOption3() == null) {
                return Arrays.asList(question.getIncorrectOption1(),
                        question.getIncorrectOption2(), question.getCorrectOption2(), question.getCorrectOption1());
            }
            /**
             * 3 correct answers
             */
            return Arrays.asList(question.getIncorrectOption1(),
                    question.getCorrectOption3(), question.getCorrectOption2(), question.getCorrectOption1());
        } else {
            return singletonList(question.getCorrectOption1());
        }
    }

    private boolean ifSingleChoiceQuestion(Question question) {
        return RADIO.equalsIgnoreCase(getQuestionType(question)) ||
                TEXT.equalsIgnoreCase(getQuestionType(question));
    }

    private boolean ifMultipleChoiceQuestion(Question question) {
        return CHECKBOX.equalsIgnoreCase(getQuestionType(question));
    }

    private String getQuestionType(Question question) {
        return question.getQuestionType().getType();
    }
}
