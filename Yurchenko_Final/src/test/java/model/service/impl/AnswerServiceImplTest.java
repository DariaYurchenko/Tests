package model.service.impl;

import model.entity.Answer;
import model.entity.status.AnswerStatus;
import model.service.AnswerService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnswerServiceImplTest {
    private static final int QUESTION_POINTS = 1;

    private AnswerService answerService;

    @Before
    public void setUp() {
        this.answerService = new AnswerServiceImpl();
    }

    @Test
    public void shouldMakeCorrectSingleChoiceAnswer() {
        String userAnswer = "correct";
        String correctAnswer = "correct";

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.CORRECT);
        Answer actual = answerService.makeSingleChoiceAnswer(QUESTION_POINTS, userAnswer, correctAnswer);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeIncorrectSingleChoiceAnswer() {
        String userAnswer = "incorrect";
        String correctAnswer = "correct";

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.INCORRECT);
        Answer actual = answerService.makeSingleChoiceAnswer(QUESTION_POINTS, userAnswer, correctAnswer);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeCorrectTextAnswer() {
        String userAnswer = "correct";
        String correctAnswer = "correct";

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.CORRECT);
        Answer actual = answerService.makeTextAnswer(QUESTION_POINTS, userAnswer, correctAnswer);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeIncorrectTextAnswer() {
        String userAnswer = "incorrect";
        String correctAnswer = "correct";

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.INCORRECT);
        Answer actual = answerService.makeTextAnswer(QUESTION_POINTS, userAnswer, correctAnswer);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeCorrectMultipleChoiceAnswerSorted() {
        String[] userAnswers = new String[] {"correct1", "correct2"};
        String[] correctAnswers =  new String[] {"correct1", "correct2"};

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.CORRECT);
        Answer actual = answerService.makeMultipleChoiceAnswer(QUESTION_POINTS, userAnswers, correctAnswers);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeCorrectMultipleChoiceAnswerNotSorted() {
        String[] userAnswers = new String[] {"correct1", "correct2"};
        String[] correctAnswers =  new String[] {"correct1", "correct2"};

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.CORRECT);
        Answer actual = answerService.makeMultipleChoiceAnswer(QUESTION_POINTS, userAnswers, correctAnswers);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeIncorrectMultipleChoiceAnswer() {
        String[] userAnswers = new String[] {"correct3", "correct2"};
        String[] correctAnswers =  new String[] {"correct1", "correct2"};

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.INCORRECT);
        Answer actual = answerService.makeMultipleChoiceAnswer(QUESTION_POINTS, userAnswers, correctAnswers);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMakeIncorrectMultipleChoiceAnswerDifferentAmount() {
        String[] userAnswers = new String[] {"correct1", "correct2", "correct3"};
        String[] correctAnswers =  new String[] {"correct1", "correct2"};

        Answer expected = new Answer(QUESTION_POINTS, AnswerStatus.INCORRECT);
        Answer actual = answerService.makeMultipleChoiceAnswer(QUESTION_POINTS, userAnswers, correctAnswers);

        assertEquals(expected, actual);
    }

}
