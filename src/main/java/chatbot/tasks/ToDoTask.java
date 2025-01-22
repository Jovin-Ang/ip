package chatbot.tasks;

public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String getDetails() {
        return "[T]" + super.getDetails();
    }
}
