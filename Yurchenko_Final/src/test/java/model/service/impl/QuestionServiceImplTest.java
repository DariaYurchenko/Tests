package model.service.impl;

import model.dao.QuestionDao;
import model.entity.Question;
import model.entity.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    QuestionDao questionDao;
    @InjectMocks
    QuestionServiceImpl questionService;

    @Test
    public void shouldFindAllQuestions() {
        when(questionDao.findAll()).thenReturn(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        List<Question> questions = questionService.findAll();
        assertNotNull(questions);
    }

    @Test
    public void shouldAddQuestion() {
        Mockito.doNothing().when(questionDao).add(new Question.Builder().build());
        questionService.addQuestion(new Question.Builder().build());
        verify(questionDao).add(new Question.Builder().build());
    }

    @Test
    public void shouldDeleteById() {
        Long questionId = 1L;
        Mockito.doNothing().when(questionDao).deleteById(questionId);
        questionService.deleteById(questionId);
        verify(questionDao).deleteById(questionId);
    }

    @Test
    public void shouldDeleteAll() {
        Mockito.doNothing().when(questionDao).deleteAll();
        questionService.deleteAll();
        verify(questionDao).deleteAll();
    }

    @Test
    public void shouldFindQuestionsByParameter() {
        String column = "question";
        String question = "question";
        when(questionDao.findByParameter(column, question)).thenReturn(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        List<Question> questions = questionService.findByParameter(column, question);
        assertNotNull(questions);
    }

    @Test
    public void shouldUpdateQuestion() {
        String column = "question";
        String question = "question";
        Long questionId = 1L;
        Mockito.doNothing().when(questionDao).update(column, question, questionId);
        questionService.update(column, question, questionId);
        verify(questionDao).update(column, question, questionId);
    }

    @Test
    public void shouldFindQuestionById() {
        Long questionId = 1L;
        when(questionDao.findById(questionId)).thenReturn(Optional.ofNullable(new Question.Builder().build()));
        Question actual = questionService.findById(questionId);
        assertNotNull(actual);
    }

    @Test
    public void shouldFindQuestionsByTheme() {
        Long themeId = 1L;
        when(questionDao.findThemeQuestions(themeId)).thenReturn(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        List<Question> questions = questionService.findQuestionsByTheme(themeId);
        assertNotNull(questions);
    }

    //TODO:???
    @Test
    public void shouldSetAnswers() {
        Map<String, Integer> startAnswers = new HashMap<>();
        startAnswers.put("rightAnswers", 1);
        startAnswers.put("AllAnswers", 2);
        Long questionId = 1L;
        Integer plusRightAnswers = 1;
        Integer plusAllAnswers = 2;
        Integer newRightAnswers = 2;
        Integer newAllAnswers = 4;

        when(questionDao.getCurrentAnswersOfQuestionFromDb(questionId)).thenReturn(startAnswers);
        Mockito.doNothing().when(questionDao).changeAmountOfAnswersInDb(questionId, newRightAnswers, newAllAnswers);

        questionService.setAnswers(questionId, plusRightAnswers, plusAllAnswers);
        verify(questionDao).getCurrentAnswersOfQuestionFromDb(questionId);
        verify(questionDao).changeAmountOfAnswersInDb(questionId, newRightAnswers, newAllAnswers);
    }

    @Test
    public void shouldFindCurrentAnswers() {
        Map<String, Integer> startAnswers = new HashMap<>();
        startAnswers.put("rightAnswers", 1);
        startAnswers.put("AllAnswers", 2);
        Long questionId = 1L;

        when(questionDao.getCurrentAnswersOfQuestionFromDb(questionId)).thenReturn(startAnswers);
        Double expected = 50.0;
        Double actual = questionService.findCurrentAnswers(questionId);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSetQuestionPoints() {
        Question radio = new Question.Builder().withQuestionType(new QuestionType("Radio")).build();
        Question checkbox = new Question.Builder().withQuestionType(new QuestionType("Checkbox")).build();
        Question text = new Question.Builder().withQuestionType(new QuestionType("Text")).build();

        int radioPoints = 1;
        int checkboxPoints = 2;
        int textPoints = 3;

        assertEquals(radioPoints, questionService.setQuestionPoints(radio));
        assertEquals(checkboxPoints, questionService.setQuestionPoints(checkbox));
        assertEquals(textPoints, questionService.setQuestionPoints(text));
    }

    @Test
    public void shouldFindQuestionsForPagination() {
        int recordsPerPage = 5;
        int startRecords = 1;

        when(questionDao.findForPagination(startRecords, recordsPerPage)).thenReturn(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        List<Question> questions = questionService.findQuestionsForPagination(startRecords, recordsPerPage);
        assertNotNull(questions);
    }

    @Test
    public void shouldFindQuestionsOfThemeForPagination() {
        int recordsPerPage = 5;
        int startRecords = 1;
        Long themeId = 1L;

        when(questionDao.findQuestionsOfThemeForPagination(startRecords, recordsPerPage, themeId)).thenReturn(Arrays.asList(new Question.Builder().build(), new Question.Builder().build()));
        List<Question> questions = questionService.findQuestionsForPagination(startRecords, recordsPerPage, themeId);
        assertNotNull(questions);
    }

}
