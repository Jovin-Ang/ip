package chatbot.storage;

import chatbot.data.TaskList;
import chatbot.data.tasks.Task;
import chatbot.exception.StorageOperationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Manages the persistence of TaskList data using JSON storage.
 * Provides functionality to save and load task data to/from JSON files.
 *
 * @author Jovin Ang
 */
public class JsonStorage {
    /**
     * The default path to save the JSON data to.
     */
    private static final String DEFAULT_SAVE_PATH = "data/tasks.json";
    /**
     * The Gson object used to serialize and deserialize JSON data.
     */
    public final Gson gson;
    /**
     * The path to save the JSON data to.
     */
    public final Path savePath;

    /**
     * Creates a new JsonStorage instance with the default save path.
     */
    public JsonStorage() {
        this(DEFAULT_SAVE_PATH);
    }

    /**
     * Creates a new JsonStorage instance with the specified save path.
     *
     * @param savePath The path to save the JSON data to.
     */
    public JsonStorage(String savePath) {
        this.savePath = Paths.get(savePath);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    /**
     * Loads task data from the JSON file.
     *
     * @return The TaskList object containing the loaded tasks.
     * @throws StorageOperationException If some error occurs while reading from the file.
     */
    public TaskList load() throws StorageOperationException {
        if (!Files.exists(savePath) || !Files.isRegularFile(savePath)) {
            return new TaskList();
        }
        try {
            String json = Files.readString(savePath);
            return gson.fromJson(json, TaskList.class);
        } catch (IOException e) {
            throw new StorageOperationException("Error reading file: " + savePath);
        } catch (JsonSyntaxException e) {
            throw new StorageOperationException("Invalid JSON data in file: " + savePath);
        }
    }

    /**
     * Saves task data to the JSON file.
     *
     * @param taskList The TaskList object containing the tasks to save.
     * @throws StorageOperationException If some error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws StorageOperationException {
        String json = gson.toJson(taskList);
        try {
            Files.createDirectories(savePath.getParent());
            Files.writeString(savePath, json);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + savePath);
        }
    }
}
