package Server.Handlers;

import Server.Requests.CreateGameRequest;
import Server.Results.CreateGameResult;
import Server.Serializers.CreateGameResultSerializer;
import Server.Services.CreateGameService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the Create Game Web API of the server.
 */
public class CreateGameHandler {
    Object dataStorage;

    public CreateGameHandler(Object storage) {
        dataStorage = storage;
    }

    public Object handleRequest(Request request, Response response) {
        CreateGameResult result;
        try {
            var gson = new Gson();
            CreateGameRequest createGameRequest = gson.fromJson(request.body(), CreateGameRequest.class);
            CreateGameService createGameService = new CreateGameService(dataStorage);
            result = createGameService.createGame(createGameRequest, request.headers("authorization"));
        } catch (JsonSyntaxException exception) {
            result = new CreateGameResult(400, "Error: bad request");
        }
        response.type("application/json");
        response.status(result.resultCode);
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(CreateGameResult.class, new CreateGameResultSerializer());
        return builder.create().toJson(result);
    }
}
