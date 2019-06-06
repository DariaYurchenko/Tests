package controller.command;

import controller.command.result.CommandResult;
import controller.pages.CommandPages;
import model.entity.Answer;
import model.entity.Question;
import model.entity.QuestionType;
import model.entity.status.AnswerStatus;
import model.service.AnswerService;
import model.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ShowRightAnswersTest {
    private static final List<Answer> ANSWERS = new ArrayList<>(Collections.singletonList(new Answer(1,
            AnswerStatus.CORRECT)));
    private static final Question RADIO = new Question.Builder()
            .withId(1)
            .withIncorrectOption1("incor1")
            .withIncorrectOption2("incor2")
            .withIncorrectOption3("incor3")
            .withCorrectOption1("cor1")
            .withPercentOfRightAnswers(50.5)
            .withQuestionType(new QuestionType("Radio"))
            .build();
    private static final Question CHECKBOX = new Question.Builder()
            .withId(1)
            .withIncorrectOption1("incor1")
            .withIncorrectOption2("incor2")
            .withCorrectOption1("cor1")
            .withCorrectOption2("cor2")
            .withPercentOfRightAnswers(50.5)
            .withQuestionType(new QuestionType("Checkbox"))
            .build();
    private static final Question TEXT = new Question.Builder()
            .withId(1)
            .withIncorrectOption1("incor1")
            .withIncorrectOption2("incor2")
            .withIncorrectOption3("incor3")
            .withCorrectOption1("cor1")
            .withPercentOfRightAnswers(50.5)
            .withQuestionType(new QuestionType("Text"))
            .build();
    private static final String USER_ANSWER = "cor1";
    private static final String[] USER_ANSWERS = new String[] {"cor1", "cor2"};
    private static final Answer ANSWER = new Answer(1, AnswerStatus.CORRECT);

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    QuestionService questionService;
    @Mock
    AnswerService answerService;
    @InjectMocks
    ShowRightAnswers showRightAnswers;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameter
    public String redirectPage;
    @Parameterized.Parameter(1)
    public Question question;
    @Parameterized.Parameter(2)
    public String userAnswer;
    @Parameterized.Parameter(3)
    public String[] userAnswers;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.PASS_TESTS, RADIO, null, null},
                {CommandPages.PASS_TESTS, RADIO, USER_ANSWER, null},
                {CommandPages.PASS_TESTS, TEXT, USER_ANSWER, null},
                {CommandPages.PASS_TESTS, CHECKBOX, null, USER_ANSWERS},
        });
    }

    @Test
    public void shouldShowRightAnswer() {
        String[] correctAnswers = new String[] {question.getCorrectOption1(), question.getCorrectOption2()};

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("counter")).thenReturn("0");
        when(request.getParameter("singleChoice")).thenReturn(USER_ANSWER);
        when(request.getParameterValues("multipleChoice")).thenReturn(USER_ANSWERS);
        when(session.getAttribute("question")).thenReturn(question);
        when(session.getAttribute("userAnswers")).thenReturn(ANSWERS);
        when(questionService.setQuestionPoints(question)).thenReturn(2);
        when(answerService.makeMultipleChoiceAnswer(2, USER_ANSWERS, correctAnswers)).thenReturn(ANSWER);
        when(answerService.makeSingleChoiceAnswer(2, USER_ANSWER, question.getCorrectOption1())).thenReturn(ANSWER);
        doNothing().when(request).setAttribute(any(), anyString());
        doNothing().when(session).setAttribute(any(), anyString());

        CommandResult commandResult = showRightAnswers.execute(request, response);

        assertEquals(redirectPage, commandResult.getPage());
    }
}
