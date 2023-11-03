package Server.Handlers;

import Server.Requests.LogoutRequest;
import Server.Results.LogoutResult;
import Server.Serializers.LogoutResultSerializer;
import Server.Services.LogoutService;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the Logout Web API of the server.
 */
public class LogoutHandler {
    Object dataStorage;

    public LogoutHandler(Object storage) {
        dataStorage = storage;
    }

    public Object handleRequest(Request request, Response response) {
        LogoutResult result;
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.authorization = request.headers("Authorization");
        LogoutService logoutService = new LogoutService(dataStorage);
        result = logoutService.logout(logoutRequest);
        response.status(result.resultCode);
        response.type("application/json");
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(LogoutResult.class, new LogoutResultSerializer());
        return builder.create().toJson(result);
    }

}
