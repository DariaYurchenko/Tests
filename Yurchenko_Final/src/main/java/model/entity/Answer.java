package model.entity;

import model.entity.entityenum.AnswerStatus;

public class Answer {

    //TODO: different types of answers: field, some correct answers

    private int maxPoints;
    private AnswerStatus answerStatus;

    public Answer(int maxPoints, AnswerStatus answerStatus) {
        this.maxPoints = maxPoints;
        this.answerStatus = answerStatus;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public AnswerStatus getAnswerStatus() {
        return answerStatus;
    }
}
