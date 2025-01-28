package chatbot.storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles JSON serialization and deserialization of LocalDateTime objects using Gson.
 * This adapter is necessary to properly handle LocalDateTime objects during JSON conversion.
 *
 * @author Jovin Ang
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    /**
     * The DateTimeFormatter used to format LocalDateTime objects.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Serializes a LocalDateTime object into its JSON representation.
     *
     * @param localDateTime The LocalDateTime object to serialize
     * @param type The type of the source object
     * @param context The serialization context
     * @return JsonElement containing the serialized LocalDateTime data
     */
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(localDateTime.format(formatter));
    }

    /**
     * Deserializes a JSON element back into a LocalDateTime object.
     *
     * @param jsonElement The JSON element to deserialize
     * @param type The type of the object to deserialize into
     * @param context The deserialization context
     * @return LocalDateTime object reconstructed from JSON
     * @throws JsonParseException if the JSON structure is invalid or class not found
     */
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), formatter);
    }
}
