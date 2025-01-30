package chatbot.ui;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import chatbot.commands.Command;
import chatbot.commands.DeadlineCommand;
import chatbot.commands.DeleteCommand;
import chatbot.commands.EventCommand;
import chatbot.commands.FindCommand;
import chatbot.commands.HelpCommand;
import chatbot.commands.ListCommand;
import chatbot.commands.MarkCommand;
import chatbot.commands.QuitCommand;
import chatbot.commands.TodoCommand;
import chatbot.commands.UnmarkCommand;
import chatbot.data.TaskList;
import chatbot.exception.InvalidCommandSyntaxException;
import chatbot.exception.StorageOperationException;
import chatbot.storage.JsonStorage;

/**
 * This class implements a chatbot.
 *
 * @author Jovin Ang
 */
public class ChatBot implements IoHandler {
    /**
     * A constant string used to visually separate sections of text output.
     * The separator is displayed both before and after chatbot messages
     * to improve readability during interactions.
     */
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * The name of the chatbot.
     */
    private final String name;
    /**
     * A Scanner object for reading user input.
     */
    private final Scanner scanner;
    /**
     * A storage manager that handles JSON serialization and deserialization of tasks.
     */
    private final JsonStorage storage = new JsonStorage();
    /**
     * A map that associates command names with their respective command implementations.
     */
    private final Map<String, Command> commands = new LinkedHashMap<>();
    /**
     * A TaskList instance, which is used to organize,
     * store, and manipulate tasks within the chatbot application.
     */
    private TaskList taskList;
    /**
     * Indicates whether the chatbot is currently running.
     * Used to manage the state of the chatbot's main operation loop.
     */
    private boolean isRunning = true;

    /**
     * Creates a new ChatBot instance.
     *
     * @param name    The name of the chatbot.
     * @param scanner The Scanner object used to handle user input.
     */
    public ChatBot(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    /**
     * Initializes the chatbot by loading tasks from storage and setting up commands.
     */
    public void init() {
        try {
            this.taskList = storage.load();
        } catch (StorageOperationException e) {
            this.send("Failed to load tasks: " + e.getMessage());
            throw new RuntimeException(e);
        }
        commands.put("help", new HelpCommand(this, commands));
        commands.put("quit", new QuitCommand(this));
        commands.put("todo", new TodoCommand(this, taskList));
        commands.put("deadline", new DeadlineCommand(this, taskList));
        commands.put("event", new EventCommand(this, taskList));
        commands.put("list", new ListCommand(this, taskList));
        commands.put("find", new FindCommand(this, taskList));
        commands.put("mark", new MarkCommand(this, taskList));
        commands.put("unmark", new UnmarkCommand(this, taskList));
        commands.put("delete", new DeleteCommand(this, taskList));
    }

    /**
     * Initiates the chatbot's interaction with the user.
     */
    public void run() {
        // Greet user
        this.send("Hi! I'm " + this.name + "\nHow can I help you today?\n(\"help\" to see what I can do)");

        while (this.isRunning) {
            String input = this.getInput();
            this.processInput(input);
        }
    }

    /**
     * Stops the chatbot and saves tasks to storage.
     */
    public void stop() {
        isRunning = false;
        // Save tasks to storage before stopping
        try {
            storage.save(taskList);
        } catch (StorageOperationException e) {
            this.send("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Processes user input by parsing the command and arguments,
     * then executing the corresponding command if it exists.
     *
     * @param input The user's input string.
     */
    private void processInput(String input) {
        String[] parts = input.split(" ", 2); // Split into command and arguments
        String command = parts[0]; // First word is the command
        String arguments = (parts.length > 1) ? parts[1] : ""; // Arguments string or empty

        // Execute command if it exists, or handle unknown command
        Command cmd = commands.get(command);
        if (cmd != null) {
            try {
                cmd.execute(arguments);
            } catch (InvalidCommandSyntaxException e) {
                this.send("Invalid command format: " + e.getMessage() + "\nUsage: " + cmd.getCommandUsage());
            }
        } else {
            this.send("Sorry. I don't understand, please try again.");
        }
    }


    /**
     * Prints a formatted message to the user.
     *
     * @param message The message to be printed.
     */
    @Override
    public void send(String message) {
        System.out.println(ChatBot.SEPARATOR + "\n" + message + "\n" + ChatBot.SEPARATOR);
    }

    /**
     * Reads and returns a line of input from the user.
     *
     * @return A string representing the user's input from the console.
     */
    @Override
    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
