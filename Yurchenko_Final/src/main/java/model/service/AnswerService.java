package model.service;

import model.entity.Answer;

public interface AnswerService {

    Answer makeAnswer(int questionPoints, String userAnswer, String correctAnswer);

    Answer makeMultipleChoiceAnswer(int questionPoints, String[] userAnswers, String[] correctAnswers);

    Answer makeTextAnswer(int questionPoints, String userAnswer, String correctAnswer);
}
