package Server.Serializers;

import Server.Results.JoinGameResult;
import com.google.gson.*;

import java.lang.reflect.Type;

public class JoinGameResultSerializer implements JsonSerializer<JoinGameResult> {
    @Override
    public JsonElement serialize(JoinGameResult joinGameResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if (joinGameResult.resultCode != 200) {
            output.add("message", new JsonPrimitive(joinGameResult.message));
        }
        return output;
    }
}
