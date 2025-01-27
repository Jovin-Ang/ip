package chatbot.data.tasks;

public class EventTask extends Task {
    private final String startTime;
    private final String endTime;

    public EventTask(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getDetails() {
        return "[E]" + super.getDetails() + " (" + startTime + " " + endTime + ")";
    }
}
