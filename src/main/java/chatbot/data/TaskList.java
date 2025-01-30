package chatbot.data;

import java.util.ArrayList;

import chatbot.data.tasks.Task;
import chatbot.exception.IllegalTaskStateChangeException;

/**
 * The TaskList class encapsulates an arraylist of tasks.
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

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index from the task list.
     *
     * @param i The index of the task to be removed.
     * @return The task that was removed from the list.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public Task removeTask(int i) throws IndexOutOfBoundsException {
        return tasks.remove(i);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param i The index of the task to be marked as completed.
     * @throws IllegalTaskStateChangeException If the task has already been marked as completed.
     * @throws IndexOutOfBoundsException       If the specified index is out of bounds in the task list.
     */
    public void completeTask(int i) throws IllegalTaskStateChangeException, IndexOutOfBoundsException {
        this.tasks.get(i).complete();
    }

    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param i The index of the task to be marked as incomplete.
     * @throws IllegalTaskStateChangeException If the task is already in the incomplete state.
     * @throws IndexOutOfBoundsException       If the specified index is out of bounds in the task list.
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
     *         or a message indicating there are no tasks if the list is empty.
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
