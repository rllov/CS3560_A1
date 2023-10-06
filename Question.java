import java.util.List;

public class Question {
    private String question;
    private List<AnswerOption> answerOptions;
    private QuestionType questionType;

    public Question(String question, List<AnswerOption> answerOptions, QuestionType questionType) {
        this.question = question;
        this.answerOptions = answerOptions;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public List<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
