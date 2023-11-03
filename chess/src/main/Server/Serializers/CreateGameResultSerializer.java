package Server.Serializers;

import Server.Results.CreateGameResult;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CreateGameResultSerializer implements JsonSerializer<CreateGameResult> {
    @Override
    public JsonElement serialize(CreateGameResult createGameResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if (createGameResult.resultCode != 200) {
            output.add("message", new JsonPrimitive(createGameResult.message));
            return output;
        } else {
            output.add("gameID", new JsonPrimitive(createGameResult.gameID));
            return output;
        }
    }
}
