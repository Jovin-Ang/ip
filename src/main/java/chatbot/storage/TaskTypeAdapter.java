package chatbot.storage;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import chatbot.data.tasks.Task;

/**
 * Handles JSON serialization and deserialization of Task objects using Gson.
 * This adapter is necessary to properly handle polymorphic Task classes during
 * JSON conversion.
 *
 * @author Jovin Ang
 */
public class TaskTypeAdapter implements JsonSerializer<Task>, JsonDeserializer<Task> {
    /**
     * The field name used to store the type of the task.
     */
    private static final String TYPE_FIELD = "type";
    /**
     * The field name used to store the task data.
     */
    private static final String DATA_FIELD = "data";

    /**
     * Serializes a Task object into its JSON representation.
     *
     * @param task      The Task object to serialize
     * @param typeOfSrc The type of the source object
     * @param context   The serialization context
     * @return JsonElement containing the serialized task data
     */
    @Override
    public JsonElement serialize(Task task, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty(TYPE_FIELD, task.getClass().getSimpleName());
        json.add(DATA_FIELD, context.serialize(task));
        return json;
    }

    /**
     * Deserializes a JSON element back into a Task object.
     *
     * @param json    The JSON element to deserialize
     * @param typeOfT The type of the object to deserialize into
     * @param context The deserialization context
     * @return Task object reconstructed from JSON
     * @throws JsonParseException if the JSON structure is invalid or class not found
     */
    @Override
    public Task deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get(TYPE_FIELD).getAsString();
        JsonElement data = jsonObject.get(DATA_FIELD);

        try {
            Class<?> taskClass = Class.forName("chatbot.data.tasks." + type);
            return context.deserialize(data, taskClass);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown task type: " + type);
        }
    }
}
