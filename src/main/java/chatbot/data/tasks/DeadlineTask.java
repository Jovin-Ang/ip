package chatbot.data.tasks;

import chatbot.util.DateTimeParser;

import java.time.LocalDateTime;

/**
 * The DeadlineTask class encapsulates a deadline task.
 * A deadline task is a task that contains a deadline.
 *
 * @author Jovin Ang
 */
public class DeadlineTask extends Task {
    /**
     * The deadline of the task.
     */
    private final LocalDateTime deadline;

    /**
     * Creates a deadline task.
     *
     * @param task The task.
     * @param deadline The deadline of the task.
     * @throws IllegalArgumentException If the task is null or empty or if the deadline is null.
     */
    public DeadlineTask(String task, LocalDateTime deadline) {
        super(task);
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.deadline = deadline;
    }

    /**
     * String representation of the deadline task.
     * The format includes a marker for the task type ('D' for deadline tasks).
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String getDetails() {
        return "[D]" + super.getDetails() + " (by: "
                + DateTimeParser.format(deadline) + ")";
    }
}
