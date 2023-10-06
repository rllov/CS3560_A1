import java.util.*;

public class SimulationDriver {
    public static void main(String[] args) {
        // Create a VotingService
        VotingService votingService = new VotingService();

        // Create students
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            students.add(new Student("Student" + i));
        }

        // Create a single-choice question
        Question singleChoiceQuestion = createSingleChoiceQuestion();

        // Create a multiple-choice question with 4 choices
        Question multipleChoiceQuestion = createMultipleChoiceQuestion();

        // Simulate student submissions for the single-choice question
        Random random = new Random();
        for (Student student : students) {
            // Randomly select an answer for each student
            String randomAnswer = singleChoiceQuestion.getAnswerOptions().get(random.nextInt(singleChoiceQuestion.getAnswerOptions().size())).getCode();
            student.submitAnswer(randomAnswer);
            // Submit the answer to the VotingService
            votingService.submitAnswer(singleChoiceQuestion, student, randomAnswer);
        }

        // Simulate student submissions for the multiple-choice question
        for (Student student : students) {
            // Randomly select one or more answers for each student
            StringBuilder selectedAnswers = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                if (random.nextBoolean()) {
                    String randomAnswer = multipleChoiceQuestion.getAnswerOptions().get(i).getCode();
                    student.submitAnswer(randomAnswer);
                    if (selectedAnswers.length() > 0) {
                        selectedAnswers.append(", ");
                    }
                    selectedAnswers.append(randomAnswer);
                }
            }
            // Submit the answers to the VotingService
            if (selectedAnswers.length() > 0) {
                votingService.submitAnswer(multipleChoiceQuestion, student, selectedAnswers.toString());
            }
        }

        // Display results
        votingService.displayResultsWithStudentAnswers();
    }

    // Helper method to create a single-choice question
    private static Question createSingleChoiceQuestion() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption("A", "Correct")); // For simplicity, always set "A" as the correct answer
        answerOptions.add(new AnswerOption("B", "Incorrect"));

        String questionText = "Select the correct answer (A):";

        return new Question(questionText, answerOptions, QuestionType.SINGLE_CHOICE);
    }

    // Helper method to create a multiple-choice question with 4 choices
    private static Question createMultipleChoiceQuestion() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption("A", "Option 1"));
        answerOptions.add(new AnswerOption("B", "Option 2"));
        answerOptions.add(new AnswerOption("C", "Option 3"));
        answerOptions.add(new AnswerOption("D", "Option 4"));

        String questionText = "Select one or more correct answers (A, B, C, D):";

        return new Question(questionText, answerOptions, QuestionType.MULTIPLE_CHOICE);
    }
}
