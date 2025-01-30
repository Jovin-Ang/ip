package chatbot.data.tasks;

import java.time.LocalDateTime;

import chatbot.util.DateTimeParser;

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
     * @param task      The task.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     * @throws IllegalArgumentException If the task is null or empty or if the start time or end time is null.
     */
    public EventTask(String task, LocalDateTime startTime, LocalDateTime endTime) {
        super(task);
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }
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
        return "[E]" + super.getDetails()
                + " (from: " + DateTimeParser.format(startTime)
                + " to: " + DateTimeParser.format(endTime) + ")";
    }
}
