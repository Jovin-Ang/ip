package chatbot.commands;

import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.ui.IoHandler;
import chatbot.data.TaskList;

/**
 * Represents a command for finding tasks in a TaskList that match a keyword.
 * This command, when executed, retrieves the descriptions of tasks that match the keyword
 * from the provided TaskList and sends them to the output using an IoHandler.
 *
 * @author Jovin Ang
 */
public class FindCommand extends Command {
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
     * Constructs a FindCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public FindCommand(IoHandler ioHandler, TaskList taskList) {
        super("find", "search for tasks in the tasklist matching the keyword", "find <task keyword>");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the find command to search for tasks in the associated TaskList that match the keyword.
     * The tasks are sent to the output using the assigned IoHandler.
     *
     * @param arguments The arguments passed to the command for execution.
     * @throws InvalidCommandSyntaxException If the keyword is empty.
     */
    @Override
    public void execute(String arguments) throws InvalidCommandSyntaxException {
        if (arguments.isEmpty()) {
            throw new InvalidCommandSyntaxException("Uh oh, keyword should not be empty.\nIf you want to display all tasks, use `list` instead.");
        }
        if (taskList.getTotalTasks() == 0) {
            ioHandler.send("No tasks to find.");
            return;
        }
        ioHandler.send(taskList.getMatchingTaskDetails(arguments));
    }
}
