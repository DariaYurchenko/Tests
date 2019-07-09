package model.entity;

import model.entity.status.AnswerStatus;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answerToCompare = (Answer) o;
        return maxPoints == answerToCompare.maxPoints &&
                answerStatus == answerToCompare.answerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPoints, answerStatus);
    }

    @Override
    public String toString() {
        return new StringBuilder("{Answer: ")
                .append("maxPoints = ").append(maxPoints)
                .append(", answerStatus = ").append(answerStatus)
                .append("}")
                .toString();
    }

}
