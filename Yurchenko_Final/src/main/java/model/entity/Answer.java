package model.entity;

import model.entity.entityenum.AnswerStatus;

public class Answer {

    //TODO: different types of answers: field, some correct answers
    //TODO: Maybe to do payful version
    private Integer id;
    private Integer testId;
    private Integer questionId;
    private String correctAnswer;
    private String userAnswer;
    private AnswerStatus answerStatus;
}
