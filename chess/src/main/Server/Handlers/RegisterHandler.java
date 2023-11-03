package Server.Handlers;

import Server.Requests.RegisterRequest;
import Server.Results.RegisterResult;
import Server.Serializers.RegisterResultSerializer;
import Server.Services.RegisterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the Register Web API of the server.
 */
public class RegisterHandler {
    Object dataStorage;

    public RegisterHandler(Object storage) {
        dataStorage = storage;
    }

    /**
     * Default Constructor for the RegisterHandler Class.
     * TO DO - understand what the handler needs
     */
    public Object handleRequest(Request request, Response response) {
        RegisterResult result;
        try {
            var gson = new Gson();
            RegisterRequest registerRequest = gson.fromJson(request.body(), RegisterRequest.class);
            RegisterService registerService = new RegisterService(dataStorage);
            result = registerService.register(registerRequest);
            response.status(result.resultCode);
        } catch (JsonSyntaxException exception) {
            result = new RegisterResult(400, "Error: bad request");
            response.status(result.resultCode);
        }
        response.type("application/json");
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(RegisterResult.class, new RegisterResultSerializer());
        return builder.create().toJson(result);
    }

}
