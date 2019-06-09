package uitility;

import model.entity.Question;
import model.service.QuestionService;
import model.service.impl.QuestionServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        QuestionService questionService = new QuestionServiceImpl();
        List<Question> questions = questionService.findQuestionsByTheme(5);
        //System.out.println(questions);
        Collections.shuffle(questions);
        System.out.println("///////////////////////");
        //System.out.println(questions);

        List<Question> questionsRus = questions.stream()
                .map(Question::getQuestionId)
                .map(questionId -> questionService.findTranslatedQuestion(5, questionId))
                .map(optQuestion -> optQuestion.orElse(new Question.Builder().build()))
                .collect(Collectors.toList());
        System.out.println(questionsRus);
        System.out.println("///////////////////////");
        System.out.println(questions);
    }
}
