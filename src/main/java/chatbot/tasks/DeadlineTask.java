package chatbot.tasks;

public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getDetails() {
        return "[D]" + super.getDetails() + " (" + deadline + ")";
    }
}
