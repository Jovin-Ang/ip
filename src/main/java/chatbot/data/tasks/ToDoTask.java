package chatbot.data.tasks;

/**
 * The ToDoTask class encapsulates a to-do task.
 * A to-do task is a standard task that contains a task.
 *
 * @author Jovin Ang
 */
public class ToDoTask extends Task {
    /**
     * Creates a to-do task.
     *
     * @param task The task.
     * @throws IllegalArgumentException If the task is null or empty.
     */
    public ToDoTask(String task) {
        super(task);
    }

    /**
     * String representation of the to-do task.
     * The format includes a marker for the task type ('T' for to-do tasks).
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String getDetails() {
        return "[T]" + super.getDetails();
    }
}
