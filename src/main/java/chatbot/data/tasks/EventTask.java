package chatbot.data.tasks;

import chatbot.util.DateTimeParser;

import java.time.LocalDateTime;

/**
 * The EventTask class encapsulates an event task.
 * An event task is a task that contains a start time and an end time.
 *
 * @author Jovin Ang
 */
public class EventTask extends Task {
    /**
     * The start time of the event.
     */
    private final LocalDateTime startTime;
    /**
     * The end time of the event.
     */
    private final LocalDateTime endTime;

    /**
     * Creates an event task.
     *
     * @param task The task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public EventTask(String task, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * String representation of the event task.
     * The format includes a marker for the task type ('E' for event tasks).
     *
     * @return A string representation of the event task.
     */
    @Override
    public String getDetails() {
        return "[E]" + super.getDetails() +
                " (from: " + DateTimeParser.format(startTime) +
                " to: " + DateTimeParser.format(endTime) + ")";
    }
}
