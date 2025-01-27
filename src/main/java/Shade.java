import chatbot.ui.ChatBot;

import java.util.Scanner;

/**
 * The main class for now
 *
 * @author Jovin Ang
 */
public class Shade {
    public static void main(String[] args) {
        // Create a scanner to read from standard input
        Scanner scanner = new Scanner(System.in);

        // Create chatbot
        ChatBot shade = new ChatBot("Shade", scanner);

        // Run the chatbot
        shade.run();

        // Clean up
        scanner.close();
    }
}
