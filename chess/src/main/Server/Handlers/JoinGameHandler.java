package Server.Handlers;

import Server.Requests.JoinGameRequest;
import Server.Results.JoinGameResult;
import Server.Serializers.JoinGameResultSerializer;
import Server.Services.JoinGameService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the Join Game Web API of the server.
 */
public class JoinGameHandler {
    Object dataStorage;

    public JoinGameHandler(Object storage) {
        dataStorage = storage;
    }

    public Object handleRequest(Request request, Response response) {
        var gson = new Gson();
        JoinGameRequest joinGameRequest = gson.fromJson(request.body(), JoinGameRequest.class);
        String authToken = request.headers("Authorization");
        JoinGameService joinGameService = new JoinGameService(dataStorage);
        JoinGameResult result = joinGameService.joinGame(joinGameRequest, authToken);
        response.type("application/json");
        response.status(result.resultCode);
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(JoinGameResult.class, new JoinGameResultSerializer());
        return builder.create().toJson(result);
    }
}
