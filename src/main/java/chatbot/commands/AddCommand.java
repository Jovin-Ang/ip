package chatbot.commands;

import chatbot.IoHandler;
import chatbot.TaskList;

/**
 * Represents a command for adding a new task to a TaskList.
 *
 * @author Jovin Ang
 */
public class AddCommand implements Command {
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
     * Constructs an AddCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public AddCommand(IoHandler ioHandler, TaskList taskList) {
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the add command to create a new task and add it to the associated TaskList.
     *
     * @param arguments The description of the task to be added.
     */
    @Override
    public void execute(String arguments) {
        taskList.createTask(arguments);
        ioHandler.send("Task added: " + arguments);
    }
}
