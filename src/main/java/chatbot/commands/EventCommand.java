package chatbot.commands;

import chatbot.IoHandler;
import chatbot.TaskList;
import chatbot.tasks.EventTask;

/**
 * Represents a command for adding a new event task to a TaskList.
 *
 * @author Jovin Ang
 */
public class EventCommand extends Command {
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
     * Constructs an EventCommand with the specified TaskList and IoHandler.
     *
     * @param taskList  The TaskList instance containing the collection of tasks to manage.
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public EventCommand(IoHandler ioHandler, TaskList taskList) {
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the event command to create a new event task and add it to the associated TaskList.
     *
     * @param arguments The description of the task to be added.
     */
    @Override
    public void execute(String arguments) {
        String[] parts = arguments.split(" /", 3);
        EventTask newEventTask = new EventTask(parts[0], parts[1], parts[2]);
        taskList.addTask(newEventTask);
        ioHandler.send("Got it. I've added this task:\n  "
                + newEventTask.getDetails() + "\nYou have "
                + taskList.getTotalTasks() + " tasks in the list.");
    }
}
