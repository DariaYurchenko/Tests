package model.entity;

import model.entity.status.AnswerStatus;
import java.util.Objects;

public class Answer {
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
        Answer answer = (Answer) o;
        return maxPoints == answer.maxPoints &&
                answerStatus == answer.answerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPoints, answerStatus);
    }
}
