package chatbot.commands;

import java.time.LocalDateTime;

import chatbot.data.TaskList;
import chatbot.data.tasks.DeadlineTask;
import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.ui.IoHandler;
import chatbot.util.DateTimeParser;

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
        super("deadline", "adds a deadline task to the tasklist", "deadline <task> /by <YYYY-MM-DD> [HH:mm]");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the deadline command to create a new deadline task and add it to the associated TaskList.
     *
     * @param arguments The description of the task to be added and its deadline.
     * @throws InvalidCommandSyntaxException If the task description or deadline is empty/invalid.
     */
    @Override
    public void execute(String arguments) throws InvalidCommandSyntaxException {
        if (arguments.isEmpty()) {
            throw new InvalidCommandSyntaxException("Uh oh, task should not be empty!");
        }

        // Split the arguments into task description and deadline
        String[] parts = arguments.split(" /by ", 2);

        if (parts.length != 2) {
            throw new InvalidCommandSyntaxException("Expected 2 arguments, only got " + parts.length + ".");
        }

        LocalDateTime deadline;
        try {
            deadline = DateTimeParser.parse(parts[1]);
        } catch (Exception e) {
            throw new InvalidCommandSyntaxException("Invalid date format! Use 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm'.");
        }

        assert deadline != null : "Deadline should not be null";
        assert !parts[0].isEmpty() : "Task description should not be empty";

        DeadlineTask newDeadlineTask = new DeadlineTask(parts[0], deadline);
        taskList.addTask(newDeadlineTask);
        ioHandler.send("Got it. I've added this task:\n  "
                + newDeadlineTask.getDetails() + "\nYou have "
                + taskList.getTotalTasks() + " tasks in the list.");
    }
}
