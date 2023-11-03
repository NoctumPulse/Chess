package Server.Handlers;

import Server.Requests.LoginRequest;
import Server.Results.LoginResult;
import Server.Serializers.LoginResultSerializer;
import Server.Services.LoginService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the Login Web API of the server.
 */
public class LoginHandler {
    Object dataStorage;

    public LoginHandler(Object storage) {
        dataStorage = storage;
    }

    public Object handleRequest(Request request, Response response) {
        LoginResult result;
        try {
            var gson = new Gson();
            LoginRequest loginRequest = gson.fromJson(request.body(), LoginRequest.class);
            LoginService loginService = new LoginService(dataStorage);
            result = loginService.login(loginRequest);
            response.status(result.resultCode);
        } catch (JsonSyntaxException exception) {
            result = new LoginResult(500, exception.getMessage());
            response.status(result.resultCode);
        }
        response.type("application/json");
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(LoginResult.class, new LoginResultSerializer());
        return builder.create().toJson(result);
    }

}
