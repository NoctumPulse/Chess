package Server.Handlers;

import Server.Results.ClearResult;
import Server.Serializers.ClearResultSerializer;
import Server.Services.ClearService;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

/**
 * Handler Class for the Clear Web API of the server.
 */
public class ClearHandler {
    Object dataStorage;

    public ClearHandler(Object storage) {
        dataStorage = storage;
    }

    public Object handleRequest(Request request, Response response) {
        ClearService service = new ClearService(dataStorage);
        ClearResult result = service.clear();
        response.type("application/json");
        response.status(result.resultCode);
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(ClearResult.class, new ClearResultSerializer());
        return builder.create().toJson(result);
    }

}
