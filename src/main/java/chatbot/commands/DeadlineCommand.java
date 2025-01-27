package chatbot.commands;

import chatbot.IoHandler;
import chatbot.TaskList;
import chatbot.tasks.DeadlineTask;

/**
 * Represents a command for adding a new deadline task to a TaskList.
 *
 * @author Jovin Ang
 */
public class DeadlineCommand extends Command {
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
     * Constructs a DeadlineCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public DeadlineCommand(IoHandler ioHandler, TaskList taskList) {
        super("deadline", "adds a deadline task to the tasklist", "deadline <task> /<by when>");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the deadline command to create a new deadline task and add it to the associated TaskList.
     *
     * @param arguments The description of the task to be added.
     */
    @Override
    public void execute(String arguments) {
        if (arguments.isEmpty()) {
            ioHandler.send("Uh oh, task should not be empty!");
            return;
        }
        String[] parts = arguments.split(" /", 2);
        if (parts.length != 2) {
            ioHandler.send("Expected 2 arguments, only got " + parts.length + ".");
            return;
        }
        DeadlineTask newDeadlineTask = new DeadlineTask(parts[0], parts[1]);
        taskList.addTask(newDeadlineTask);
        ioHandler.send("Got it. I've added this task:\n  "
                + newDeadlineTask.getDetails() + "\nYou have "
                + taskList.getTotalTasks() + " tasks in the list.");
    }
}
