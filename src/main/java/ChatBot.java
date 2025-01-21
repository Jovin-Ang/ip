import java.util.Scanner;

/**
 * This class implements a chatbot.
 *
 * @author Jovin Ang
 */
public class ChatBot {
    /**
     * The name of the chatbot.
     */
    private final String name;
    /**
     * A Scanner object for reading user input.
     */
    private final Scanner scanner;
    /**
     * A TaskList instance, which is used to organize,
     * store, and manipulate tasks within the chatbot application.
     */
    private final TaskList tasklist = new TaskList();

    /**
     * A constant string used to visually separate sections of text output.
     * The separator is displayed both before and after chatbot messages
     * to improve readability during interactions.
     */
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Creates a new ChatBot instance.
     *
     * @param name The name of the chatbot.
     * @param scanner The Scanner object used to handle user input.
     */
    public ChatBot(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    /**
     * Initiates the chatbot's interaction with the user.
     */
    public void run() {
        // Greet user
        this.send("Hi! I'm " + this.name + "\nHow can I you today?");

        boolean chaton = true;
        // Main loop
        while (chaton) {
            String input = this.getInput();

            switch (input) {
                case "list" -> this.send(tasklist.toString());
                case "quit" -> chaton = false;
                default -> {
                    this.tasklist.addTask(input);
                    this.send("Added task: " + input);
                }
            }
        }

        // Goodbye to user
        this.send("Bye :) Hope to see you again soon!");
    }

    /**
     * Prints a formatted message to the user.
     *
     * @param message The message to be printed.
     */
    private void send(String message) {
        System.out.println(SEPARATOR + "\n" + message + "\n" + SEPARATOR);
    }

    /**
     * Reads and returns a line of input from the user.
     *
     * @return A string representing the user's input from the console.
     */
    private String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }
}
