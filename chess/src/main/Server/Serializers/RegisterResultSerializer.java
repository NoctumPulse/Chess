package Server.Serializers;

import Server.Results.RegisterResult;
import com.google.gson.*;
import java.lang.reflect.Type;

public class RegisterResultSerializer implements JsonSerializer<RegisterResult> {
    @Override
    public JsonElement serialize(RegisterResult registerResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if(registerResult.resultCode != 200){
            output.add("message", new JsonPrimitive(registerResult.message));
        }
        else{
            output.add("username", new JsonPrimitive(registerResult.username));
            output.add("authToken", new JsonPrimitive(registerResult.authToken));
        }
        return output;
    }
}