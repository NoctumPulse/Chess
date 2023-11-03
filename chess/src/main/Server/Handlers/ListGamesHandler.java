package Server.Handlers;
import Server.Requests.ListGamesRequest;
import Server.Results.ListGamesResult;
import Server.Services.ListGamesService;
import Server.Serializers.ListGamesResultSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the List Games Web API of the server.
 */
public class ListGamesHandler {
    Object dataStorage;
    public ListGamesHandler(Object storage){
        dataStorage = storage;
    }
    public Object handleRequest(Request request, Response response){
        ListGamesResult result;
        try {
            var gson = new Gson();
            ListGamesRequest listGamesRequest = gson.fromJson(request.body(), ListGamesRequest.class);
            ListGamesService registerService = new ListGamesService(dataStorage);
            result = registerService.listGames(listGamesRequest, request.headers("Authorization"));
        }
        catch(JsonSyntaxException exception){
            result = new ListGamesResult(500, "Error: bad request");
        }
        response.type("application/json");
        response.status(result.resultCode);
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(ListGamesResult.class, new ListGamesResultSerializer());
        System.out.println(builder.create().toJson(result));
        return builder.create().toJson(result);
    }
}
