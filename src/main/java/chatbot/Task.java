package chatbot;

/**
 * The chatbot.Task class encapsulates a task.
 * Each task has a name.
 *
 * @author Jovin Ang
 */
public class Task {
    /**
     * Identifier or label of the task.
     */
    private final String task;
    /**
     * Indicates whether the task has been completed.
     * Initially set to false.
     */
    private boolean isCompleted;

    /**
     * Creates a task.
     *
     * @param task The task.
     */
    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     * If the task is already marked as completed, an IllegalTaskStateChangeException exception is thrown.
     *
     * @throws IllegalTaskStateChangeException if the task is already in the completed state.
     */
    public void complete() throws IllegalTaskStateChangeException {
        if (!this.isCompleted)
            this.isCompleted = true;
        else
            throw new IllegalTaskStateChangeException(this.task, "completed", "completed");
    }

    /**
     * Marks the task as incomplete if it is currently in the completed state.
     * If the task is already incomplete, an IllegalTaskStateChangeException exception is thrown.
     *
     * @throws IllegalTaskStateChangeException if the task is already in the incomplete state.
     */
    public void incomplete() throws IllegalTaskStateChangeException {
        if (this.isCompleted)
            this.isCompleted = false;
        else
            throw new IllegalTaskStateChangeException(this.task, "incomplete", "incomplete");
    }

    /**
     * Provides a string representation of the task, including its completion status.
     * The format includes a marker for completion status ('X' for completed, ' ' for incomplete)
     * followed by the task's name.
     *
     * @return A string representation of the task with its completion status.
     */
    public String getDetails() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this;
    }

    /**
     * Returns the name of the task as its string representation.
     *
     * @return A string representing the name of the task.
     */
    @Override
    public String toString() {
        return this.task;
    }
}
