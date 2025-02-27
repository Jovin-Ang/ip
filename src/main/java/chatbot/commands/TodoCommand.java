package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.data.tasks.ToDoTask;
import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.ui.IoHandler;

/**
 * Represents a command for adding a new todotask to a TaskList.
 *
 * @author Jovin Ang
 */
public class TodoCommand extends Command {
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
     * Constructs a ToDoCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public TodoCommand(IoHandler ioHandler, TaskList taskList) {
        super("todo", "adds a task to the tasklist", "todo <task>");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the todocommand to create a new todotask and add it to the associated TaskList.
     *
     * @param arguments The description of the task to be added.
     * @throws InvalidCommandSyntaxException If the task description is empty.
     */
    @Override
    public void execute(String arguments) throws InvalidCommandSyntaxException {
        if (arguments.isEmpty()) {
            throw new InvalidCommandSyntaxException("Uh oh, task should not be empty!");
        }

        ToDoTask newToDoTask = new ToDoTask(arguments);
        taskList.addTask(newToDoTask);
        ioHandler.send("Got it. I've added this task:\n  "
                + newToDoTask.getDetails() + "\nYou have "
                + taskList.getTotalTasks() + " tasks in the list.");
    }
}
