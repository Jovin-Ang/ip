package chatbot;

import java.io.IOException;

import chatbot.ui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main JavaFX application class for the Shade chatbot.
 * This class handles the initialization and startup of the GUI-based chatbot.
 *
 * @author Jovin Ang
 */
public class Shade extends Application {
    private static final String CHATBOT_NAME = "Shade";

    private MainController controller;

    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and starts the JavaFX application.
     * Sets up the primary stage and initializes the chatbot.
     *
     * @param primaryStage The primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainWindow.fxml"));
        Parent root = loader.load();

        // Create chatbot
        controller = loader.getController();
        controller.setChatBot(CHATBOT_NAME);

        // Set up stage
        primaryStage.setTitle(CHATBOT_NAME + " Chatbot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Stops the JavaFX application.
     * Shuts down the chatbot and saves the tasks.
     */
    @Override
    public void stop() {
        controller.shutdown();
    }
}
