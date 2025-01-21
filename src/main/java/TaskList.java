import java.util.ArrayList;

/**
 * The TaskList class encapsulates an arraylist of tasks.
 *
 * @author Jovin Ang
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String taskName) {
        tasks.add(new Task(taskName));
    }

    @Override
    public String toString() {
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No tasks, yay!");
    }
}
