package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.ui.IoHandler;

/**
 * Represents a command for listing tasks in a TaskList.
 * This command, when executed, retrieves the descriptions of all tasks
 * from the provided TaskList and sends them to the output using an IoHandler.
 *
 * @author Jovin Ang
 */
public class ListCommand extends Command {
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
     * Constructs a ListCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to list.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public ListCommand(IoHandler ioHandler, TaskList taskList) {
        super("list", "shows all tasks in a list", "list");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the ListCommand by retrieving and printing a list of task descriptions
     * from the associated TaskList. The tasks are sent to the output using the assigned IoHandler.
     *
     * @param arguments This parameter is ignored as the command does not require arguments
     *                  to perform its function.
     */
    @Override
    public void execute(String arguments) {
        ioHandler.send(taskList.getTaskDetails()); // Print the task list with details
    }
}
