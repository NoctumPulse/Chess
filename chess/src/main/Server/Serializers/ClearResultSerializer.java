package Server.Serializers;

import Server.Results.ClearResult;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ClearResultSerializer implements JsonSerializer<ClearResult> {
    @Override
    public JsonElement serialize(ClearResult clearResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if (clearResult.resultCode != 200) {
            output.add("message", new JsonPrimitive(clearResult.message));
            return output;
        }
        return output;
    }
}