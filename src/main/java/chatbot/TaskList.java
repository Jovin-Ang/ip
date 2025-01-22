package chatbot;

import java.util.ArrayList;

/**
 * The chatbot.TaskList class encapsulates an arraylist of tasks.
 *
 * @author Jovin Ang
 */
public class TaskList {
    /**
     * A list used to store and manage tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList instance.
     * Initializes an empty list to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void createTask(String taskName) {
        tasks.add(new Task(taskName));
    }

    /**
     * Mark a task as complete.
     *
     * @param i The index of the task to complete.
     */
    public void completeTask(int i) throws IllegalTaskStateChangeException, IndexOutOfBoundsException {
        this.tasks.get(i).complete();
    }

    /**
     * Mark a task as incomplete.
     *
     * @param i The index of the task to incomplete.
     */
    public void incompleteTask(int i) throws IllegalTaskStateChangeException, IndexOutOfBoundsException {
        this.tasks.get(i).incomplete();
    }

    /**
     * Returns the total number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasks() {
        return tasks.size();
    }

    /**
     * Returns a concatenated string of task descriptions, where each task is listed with its
     * index followed by its string representation. The resulting string separates tasks by
     * newlines. If the task list is empty, a default message indicating no tasks is returned.
     *
     * @return A string containing the indexed descriptions of all tasks in the list,
     * or a message indicating there are no tasks if the list is empty.
     */
    public String getTaskDescriptions() {
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No tasks, yay!");
    }

    /**
     * Produces a string representation of the task list. Each task is displayed with its index
     * and detailed information. If the task list is empty, a default message is returned indicating
     * no tasks are present.
     *
     * @return A string representation of all tasks in the list with indices and details.
     */
    public String getTaskDetails() {
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.getDetails())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No tasks, yay!");
    }

    /**
     * Returns a string representation of the task list, where each task is listed with its
     * index and description. If no tasks are present, a default message is returned indicating
     * an empty task list.
     *
     * @return A string representation of all tasks in the task list.
     */
    @Override
    public String toString() {
        return this.getTaskDescriptions();
    }
}
