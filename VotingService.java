import java.util.*;

public class VotingService {
    private Map<Question, Map<Student, String>> submissions = new HashMap<>();

    public void submitAnswer(Question question, Student student, String answer) {
        if (!submissions.containsKey(question)) {
            submissions.put(question, new HashMap<>());
        }
        submissions.get(question).put(student, answer);
    }

    public void displayResults() {
        for (Question question : submissions.keySet()) {
            Map<String, Integer> answerCounts = new HashMap<>();
            List<AnswerOption> answerOptions = question.getAnswerOptions();
            
            for (AnswerOption option : answerOptions) {
                answerCounts.put(option.getCode(), 0);
            }

            Map<Student, String> studentAnswers = submissions.get(question);
            for (String studentAnswer : studentAnswers.values()) {
                if (answerCounts.containsKey(studentAnswer)) {
                    answerCounts.put(studentAnswer, answerCounts.get(studentAnswer) + 1);
                }
            }

            System.out.println("Results for Question: " + question.getQuestion());
            for (AnswerOption option : answerOptions) {
                System.out.println(option.getCode() + " : " + answerCounts.get(option.getCode()));
            }
            System.out.println();
        }
    }

    public void displayResultsWithStudentAnswers() {
        for (Question question : submissions.keySet()) {
            Map<Student, String> studentAnswers = submissions.get(question);

            System.out.println("Results for Question: " + question.getQuestion());
            for (Student student : studentAnswers.keySet()) {
                String studentID = student.getId();
                String studentAnswer = studentAnswers.get(student);

                System.out.println("Student ID: " + studentID + ", Answer: " + studentAnswer);
            }

            System.out.println();
        }
    }

}
