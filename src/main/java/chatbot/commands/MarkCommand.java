package chatbot.commands;

import chatbot.IllegalTaskStateChangeException;
import chatbot.IoHandler;
import chatbot.TaskList;

/**
 * Represents a command that marks a task in a TaskList as completed.
 *
 * @author Jovin Ang
 */
public class MarkCommand extends Command {
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
    public MarkCommand(IoHandler ioHandler, TaskList taskList) {
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the mark command to mark a task as completed based on the provided task number.
     * Sends appropriate confirmation or error messages via the associated IoHandler.
     *
     * @param arguments The task number to be marked as completed.
     */
    @Override
    public void execute(String arguments) {
        if (taskList.getTotalTasks() == 0) {
            ioHandler.send("No tasks to mark.");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(arguments);
            taskList.completeTask(taskNumber - 1);
            ioHandler.send("Marked task " + taskNumber + " as completed.");
        } catch (IllegalTaskStateChangeException e) {
            ioHandler.send(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ioHandler.send("Invalid task number. Ensure the number is between 1 and " + taskList.getTotalTasks() + ".");
        } catch (Exception e) {
            ioHandler.send("Invalid task number. Use: mark <taskNumber>");
        }
    }
}
