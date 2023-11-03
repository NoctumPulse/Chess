package Server.Serializers;

import Server.Results.LogoutResult;
import com.google.gson.*;

import java.lang.reflect.Type;

public class LogoutResultSerializer implements JsonSerializer<LogoutResult> {
    @Override
    public JsonElement serialize(LogoutResult logoutResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if (logoutResult.resultCode != 200) {
            output.add("message", new JsonPrimitive(logoutResult.message));
        }
        return output;
    }
}