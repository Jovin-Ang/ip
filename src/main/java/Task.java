/**
 * The Task class encapsulates a task.
 * Each task has a name.
 *
 * @author Jovin Ang
 */
public class Task {
    /**
     * Identifier or label of the task.
     */
    private final String name;

    /**
     * Creates a task with a name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * String representation of the task.
     *
     * @return The name of the task.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
