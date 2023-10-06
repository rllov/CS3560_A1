public class Student {
    private String id;
    private String answer;

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void submitAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

	
}
