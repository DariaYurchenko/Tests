package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Question;
import model.entity.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowQuestionTest {
    private static final List<Question> QUESTIONS = new ArrayList<>(Arrays.asList(new Question.Builder()
                    .withId(1)
                    .withIncorrectOption1("incor1")
                    .withIncorrectOption2("incor2")
                    .withIncorrectOption3("incor3")
                    .withCorrectOption1("cor1")
                    .withQuestionType(new QuestionType("Radio"))
                    .build(),
            new Question.Builder()
                    .withId(1)
                    .withIncorrectOption1("incor1")
                    .withIncorrectOption2("incor2")
                    .withCorrectOption1("cor1")
                    .withCorrectOption2("cor2")
                    .withQuestionType(new QuestionType("Checkbox"))
                    .build(),
            new Question.Builder()
                    .withId(1)
                    .withIncorrectOption1("incor1")
                    .withIncorrectOption2("incor2")
                    .withIncorrectOption3("incor3")
                    .withCorrectOption1("cor1")
                    .withQuestionType(new QuestionType("Text"))
                    .build()));

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @InjectMocks
    ShowQuestion showQuestion;

    @Test
    public void shouldShowQuestion() {
        when(request.getParameter("counter")).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("questions")).thenReturn(QUESTIONS);
        when(session.getAttribute("rusQuestions")).thenReturn(QUESTIONS);

        CommandResult commandResult = showQuestion.execute(request, response);
        assertEquals(CommandPages.PASS_TESTS, commandResult.getPage());
    }

}
