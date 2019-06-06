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
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class PassTestTest {
  /*  private static final List<Question> QUESTIONS = new ArrayList<>(Arrays.asList(new Question.Builder()
                    .withId(1L)
                    .withIncorrectOption1("incor1")
                    .withIncorrectOption2("incor2")
                    .withIncorrectOption3("incor3")
                    .withCorrectOption1("cor1")
                    .withQuestionType(new QuestionType("Radio"))
                    .build(),
            new Question.Builder()
                    .withId(1L)
                    .withIncorrectOption1("incor1")
                    .withIncorrectOption2("incor2")
                    .withCorrectOption1("cor1")
                    .withCorrectOption2("cor2")
                    .withQuestionType(new QuestionType("Checkbox"))
                    .build(),
            new Question.Builder()
                    .withId(1L)
                    .withIncorrectOption1("incor1")
                    .withIncorrectOption2("incor2")
                    .withIncorrectOption3("incor3")
                    .withCorrectOption1("cor1")
                    .withQuestionType(new QuestionType("Text"))
                    .build()));
    private static final Question RADIO = new Question.Builder()
            .withId(1L)
            .withIncorrectOption1("incor1")
            .withIncorrectOption2("incor2")
            .withIncorrectOption3("incor3")
            .withCorrectOption1("cor1")
            .withQuestionType(new QuestionType("Radio"))
            .build();
    private static final Question CHECKBOX = new Question.Builder()
            .withId(1L)
            .withIncorrectOption1("incor1")
            .withIncorrectOption2("incor2")
            .withCorrectOption1("cor1")
            .withCorrectOption2("cor2")
            .withQuestionType(new QuestionType("Checkbox"))
            .build();
    private static final Question TEXT = new Question.Builder()
            .withId(1L)
            .withIncorrectOption1("incor1")
            .withIncorrectOption2("incor2")
            .withIncorrectOption3("incor3")
            .withCorrectOption1("cor1")
            .withQuestionType(new QuestionType("Text"))
            .build();
    private static final String USER_ANSWER = "cor1";
    private static final String[] USER_ANSWERS = new String[] {"cor1", "cor2"};
    private static final Answer ANSWER = new Answer(1, AnswerStatus.CORRECT);


    @Mock
    HttpSession session;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    QuestionService questionService;
    @Mock
    AnswerService answerService;
    @InjectMocks
    PassTest passTestCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameter
    public String redirectPage;
    @Parameterized.Parameter(1)
    public String forward;
    @Parameterized.Parameter(2)
    public Question question;
    @Parameterized.Parameter(3)
    public String userAnswer;
    @Parameterized.Parameter(4)
    public String[] userAnswers;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {CommandPages.PASS_TESTS, null, RADIO, null, null},
                {CommandPages.PASS_TESTS, "TRUE", RADIO, USER_ANSWER, null},
                {CommandPages.PASS_TESTS, "TRUE", TEXT, USER_ANSWER, null},
                {CommandPages.PASS_TESTS, "TRUE", CHECKBOX, null, USER_ANSWERS},
        });
    }

    @Test
    public void shouldPassTest() {
        String[] correctAnswers = new String[] {question.getCorrectOption1(), question.getCorrectOption2()};
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("questions")).thenReturn(QUESTIONS);
        when(session.getAttribute("rusQuestions")).thenReturn(QUESTIONS);
        when(request.getParameter("counter")).thenReturn("0");
        when(request.getParameter("forward")).thenReturn(forward);
        when(request.getParameter("radio")).thenReturn(USER_ANSWER);
        when(request.getParameterValues("checkbox")).thenReturn(USER_ANSWERS);
        when(session.getAttribute("question")).thenReturn(question);
        when(questionService.setQuestionPoints(question)).thenReturn(2);
        when(questionService.findRightAnswersPercent(question.getQuestionId())).thenReturn(50.5);
        when(answerService.makeMultipleChoiceAnswer(2, USER_ANSWERS, correctAnswers)).thenReturn(ANSWER);
        when(answerService.makeSingleChoiceAnswer(2, USER_ANSWER, question.getCorrectOption1())).thenReturn(ANSWER);
        doNothing().when(request).setAttribute(any(), anyString());
        doNothing().when(session).setAttribute(any(), anyString());

        CommandResult commandResult = passTestCommand.execute(request, response);

        assertEquals(redirectPage, commandResult.getPage());

    }*/

}