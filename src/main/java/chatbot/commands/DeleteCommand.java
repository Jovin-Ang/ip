package chatbot.commands;

import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.ui.IoHandler;
import chatbot.data.TaskList;
import chatbot.data.tasks.Task;

/**
 * Represents a command that deletes a task in a TaskList.
 *
 * @author Jovin Ang
 */
public class DeleteCommand extends Command {
    /**
     * Reference to an IoHandler instance which Handles input and output operations for
     * the command.
     */
    private final IoHandler ioHandler;
    /**
     * Holds a reference to a TaskList instance, which encapsulates a collection of tasks.
     */
    private final TaskList taskList;

    /**
     * Constructs a MarkCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public DeleteCommand(IoHandler ioHandler, TaskList taskList) {
        super("delete", "deletes a task from the tasklist", "delete <task number>");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Deletes a task from the task list based on the provided task number.
     *
     * @param arguments The task number to be deleted.
     * @throws InvalidCommandSyntaxException If the task number is invalid.
     */
    @Override
    public void execute(String arguments) throws InvalidCommandSyntaxException {
        if (taskList.getTotalTasks() == 0) {
            ioHandler.send("No tasks to delete.");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(arguments);
            Task deletedTask = taskList.removeTask(taskNumber - 1);
            ioHandler.send("Noted. I've removed this task: \n  "
                    + deletedTask.getDetails() + "\nNow you have "
                    + taskList.getTotalTasks() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            ioHandler.send("Invalid task number. Ensure the number is between 1 and " + taskList.getTotalTasks() + ".");
        } catch (Exception e) {
            throw new InvalidCommandSyntaxException("Invalid task number.");
        }
    }
}
