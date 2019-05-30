package model.service.impl;

import model.entity.Answer;
import model.entity.status.AnswerStatus;
import model.service.AnswerService;

import java.util.Arrays;

public class AnswerServiceImpl implements AnswerService {

    @Override
    public Answer makeAnswer(int questionPoints, String userAnswer, String correctAnswer) {
        return checkIfUserAnswerCorrect(questionPoints, userAnswer, correctAnswer);
    }

    @Override
    public Answer makeMultipleChoiceAnswer(int questionPoints, String[] userAnswers, String[] correctAnswers) {
        sortAnswersLists(userAnswers, correctAnswers);
        return checkIfUserAnswerCorrect(questionPoints, userAnswers, correctAnswers);
    }

    //TODO: delete
    @Override
    public Answer makeTextAnswer(int questionPoints, String userAnswer, String correctAnswer) {
        return checkIfUserAnswerCorrect(questionPoints, userAnswer, correctAnswer);
    }

    private void sortAnswersLists(String[] userAnswers, String[] correctAnswers) {
        Arrays.sort(userAnswers);
        Arrays.sort(correctAnswers);
    }

    private Answer checkIfUserAnswerCorrect(int questionPoints, String userAnswer, String correctAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer) ? new Answer(questionPoints, AnswerStatus.CORRECT) :
                new Answer(questionPoints, AnswerStatus.INCORRECT);
    }

    private Answer checkIfUserAnswerCorrect(int questionPoints, String[] userAnswers, String[] correctAnswers) {
        return Arrays.equals(userAnswers, correctAnswers) ? new Answer(questionPoints, AnswerStatus.CORRECT) :
                new Answer(questionPoints, AnswerStatus.INCORRECT);
    }

}
