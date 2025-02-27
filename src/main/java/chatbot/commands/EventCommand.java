package chatbot.commands;

import java.time.LocalDateTime;
import java.util.Arrays;

import chatbot.data.TaskList;
import chatbot.data.tasks.EventTask;
import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.ui.IoHandler;
import chatbot.util.DateTimeParser;

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
        super("event", "adds an event task to the tasklist",
                "event <task> /from <YYYY-MM-DD> [HH:mm] /to <YYYY-MM-DD> [HH:mm]");
        this.ioHandler = ioHandler;
        this.taskList = taskList;
    }

    /**
     * Executes the event command to create a new event task and add it to the associated TaskList.
     *
     * @param arguments The description of the task to be added.
     * @throws InvalidCommandSyntaxException If the task description, start date and/or
     *                                       end date is empty/invalid.
     */
    @Override
    public void execute(String arguments) throws InvalidCommandSyntaxException {
        if (arguments.isEmpty()) {
            throw new InvalidCommandSyntaxException("Uh oh, task should not be empty!");
        }

        // Split the arguments into task description, start date and end date
        String[] parts = Arrays.stream(arguments.split(" /from | /to ", 3))
                .map(String::trim)
                .toArray(String[]::new);

        if (parts.length != 3) {
            throw new InvalidCommandSyntaxException("Expected 3 arguments, only got " + parts.length + ".");
        }

        if (parts[0].isEmpty()) {
            throw new InvalidCommandSyntaxException("Uh oh, task should not be empty!");
        }

        LocalDateTime start;
        LocalDateTime end;
        try {
            start = DateTimeParser.parse(parts[1]);
            end = DateTimeParser.parse(parts[2]);
        } catch (Exception e) {
            throw new InvalidCommandSyntaxException("Invalid date format! Use 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm'");
        }

        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";
        assert !parts[0].isEmpty() : "Task description should not be empty";

        if (end.isBefore(start)) {
            throw new InvalidCommandSyntaxException("Error: End time must be after start time");
        }

        EventTask newEventTask = new EventTask(parts[0], start, end);
        taskList.addTask(newEventTask);
        ioHandler.send("Got it. I've added this task:\n  "
                + newEventTask.getDetails() + "\nYou have "
                + taskList.getTotalTasks() + " tasks in the list.");
    }
}
