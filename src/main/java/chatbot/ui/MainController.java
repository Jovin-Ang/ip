package chatbot.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main chat interface defined in MainWindow.fxml.
 * Handles user input and displays chat history.
 *
 * @author Jovin Ang
 */
public class MainController {

    /**
     * The avatar image for the user.
     */
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png"))
    );
    /**
     * The avatar image for the chatbot.
     */
    private final Image botImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/shade.png"))
    );

    /**
     * The scroll pane for the chat history.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * The dialog container for displaying chat messages.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * The text field for user input.
     */
    @FXML
    private TextField userInput;
    /**
     * The chatbot instance for this controller.
     */
    private ChatBot chatBot;

    /**
     * Initializes the controller after FXML loading.
     * Binds chat history scrolling to bottom.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input from either text field or send button.
     */
    @FXML
    private void handleInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            chatBot.processInput(input);
            userInput.clear();
        }
    }

    /**
     * Executes cleanup tasks when the application is closed.
     */
    public void shutdown() {
        chatBot.saveTasks();
    }

    /**
     * Sets the ChatBot instance for this controller.
     *
     * @param name The name of the chatbot
     */
    public void setChatBot(String name) {
        // Create chatbot
        this.chatBot = new ChatBot(name, dialogContainer, userInput, botImage);

        // Initialize the chatbot
        this.chatBot.init();
    }
}
