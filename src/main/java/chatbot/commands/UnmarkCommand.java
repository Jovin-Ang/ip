package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.exception.IllegalTaskStateChangeException;
import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.ui.IoHandler;

/**
 * Represents a command that marks a task in a TaskList as incomplete.
 *
 * @author Jovin Ang
 */
public class UnmarkCommand extends Command {
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
     * Constructs an UnmarkCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public UnmarkCommand(IoHandler ioHandler, TaskList taskList) {
        super("unmark", "unmarks a task as done", "unmark <task number>");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the unmark command to mark a task as incomplete based on the provided task number.
     * Sends appropriate confirmation or error messages via the associated IoHandler.
     *
     * @param arguments The task number to be marked as incomplete.
     * @throws InvalidCommandSyntaxException If the task number is invalid.
     */
    @Override
    public void execute(String arguments) throws InvalidCommandSyntaxException {
        if (taskList.getTotalTasks() == 0) {
            ioHandler.send("No tasks to unmark.");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(arguments);
            assert taskNumber > 0 : "Task number should be greater than 0";
            taskList.incompleteTask(taskNumber - 1);
            ioHandler.send("Unmarked task " + taskNumber + " as completed.");
        } catch (IllegalTaskStateChangeException e) {
            ioHandler.send(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ioHandler.send("Invalid task number. Ensure the number is between 1 and " + taskList.getTotalTasks() + ".");
        } catch (Exception e) {
            throw new InvalidCommandSyntaxException("Invalid task number.");
        }
    }
}
