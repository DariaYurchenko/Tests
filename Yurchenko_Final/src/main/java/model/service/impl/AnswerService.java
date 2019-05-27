package model.service.impl;

import model.entity.Answer;
import model.entity.entityenum.AnswerStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerService {
    private List<Answer> answerList;

    public AnswerService() {
        this.answerList = new ArrayList<>();
    }

    public void addAnswerToList(int maxPoint, String userAnswer, String correctAnswer) {
        answerList.add(makeAnswer(maxPoint, userAnswer, correctAnswer));
    }

    public void addAnswerToList(int maxPoint, String[] userAnswers, String[] correctAnswers) {
        answerList.add(makeAnswer(maxPoint, userAnswers, correctAnswers));
    }

    public Answer makeAnswer(int maxPoint, String userAnswer, String correctAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer) ? new Answer(maxPoint, AnswerStatus.CORRECT) :
                new Answer(maxPoint, AnswerStatus.INCORRECT);
    }

    public Answer makeAnswer(int maxPoint, String[] userAnswers, String[] correctAnswers) {
        Arrays.sort(userAnswers);
        Arrays.sort(correctAnswers);
        return Arrays.equals(userAnswers, correctAnswers) ? new Answer(maxPoint, AnswerStatus.CORRECT) :
                new Answer(maxPoint, AnswerStatus.INCORRECT);
    }

    public Answer makeAnswerText(int maxPoint, String userAnswer, String correctAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer) ? new Answer(maxPoint, AnswerStatus.CORRECT) :
                new Answer(maxPoint, AnswerStatus.INCORRECT);
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
