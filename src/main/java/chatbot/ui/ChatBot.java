package chatbot.ui;

import java.util.LinkedHashMap;
import java.util.Map;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * This class implements a chatbot.
 *
 * @author Jovin Ang
 */
public class ChatBot implements IoHandler {
    /**
     * The name of the chatbot.
     */
    private final String name;
    /**
     * A storage manager that handles JSON serialization and deserialization of tasks.
     */
    private final JsonStorage storage = new JsonStorage();
    /**
     * A map that associates command names with their respective command implementations.
     */
    private final Map<String, Command> commands = new LinkedHashMap<>();
    /**
     * A dialog container for displaying chat messages.
     */
    private final VBox dialogContainer;
    /**
     * The text field for user input.
     */
    private final TextField userInput;
    /**
     * The avatar image for the chatbot.
     */
    private final Image botImage;
    /**
     * A TaskList instance, which is used to organize,
     * store, and manipulate tasks within the chatbot application.
     */
    private TaskList taskList;

    /**
     * Creates a new ChatBot instance.
     *
     * @param name            The name of the chatbot.
     * @param dialogContainer The dialog container for displaying chat messages.
     * @param userInput       The text field for user input.
     * @param botImage        The avatar image for the chatbot.
     */
    public ChatBot(String name, VBox dialogContainer, TextField userInput, Image botImage) {
        this.name = name;
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
        this.botImage = botImage;
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

        this.send("Hi! I'm " + this.name + "\nHow can I help you today?\n(\"help\" to see what I can do)");
    }

    /**
     * Saves tasks to storage.
     */
    public void saveTasks() {
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
    void processInput(String input) {
        String[] parts = input.split(" ", 2); // Split into command and arguments
        String command = parts[0]; // First word is the command
        String arguments = (parts.length > 1) ? parts[1] : ""; // Arguments string or empty

        // Execute command if it exists, or handle unknown command
        Command cmd = commands.get(command);
        if (cmd == null) {
            this.send("Sorry. I don't understand, please try again.");
            return;
        }

        try {
            cmd.execute(arguments);
        } catch (InvalidCommandSyntaxException e) {
            this.send("Invalid command format: " + e.getMessage() + "\nUsage: " + cmd.getCommandUsage());
        }
    }


    /**
     * Prints a formatted message to the user.
     *
     * @param message The message to be printed.
     */
    @Override
    public void send(String message) {
        dialogContainer.getChildren().add(DialogBox.getBotDialog(message, botImage));
    }

    /**
     * Reads and returns a line of input from the user.
     *
     * @return A string representing the user's input from the chat interface.
     */
    @Override
    public String getInput() {
        return userInput.getText().trim();
    }
}
