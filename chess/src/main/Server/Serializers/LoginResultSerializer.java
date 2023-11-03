package Server.Serializers;

import Server.Results.LoginResult;
import com.google.gson.*;

import java.lang.reflect.Type;

public class LoginResultSerializer implements JsonSerializer<LoginResult> {
    @Override
    public JsonElement serialize(LoginResult loginResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if (loginResult.resultCode != 200) {
            output.add("message", new JsonPrimitive(loginResult.message));
        } else {
            output.add("username", new JsonPrimitive(loginResult.username));
            output.add("authToken", new JsonPrimitive(loginResult.authToken));
        }
        return output;
    }
}