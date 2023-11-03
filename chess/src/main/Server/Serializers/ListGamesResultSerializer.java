package Server.Serializers;

import Server.Models.Game;
import Server.Results.ListGamesResult;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ListGamesResultSerializer implements JsonSerializer<ListGamesResult> {
    @Override
    public JsonElement serialize(ListGamesResult listGamesResult, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject output = new JsonObject();
        if (listGamesResult.resultCode != 200) {
            output.add("message", new JsonPrimitive(listGamesResult.message));
        } else {
            JsonArray gameArray = new JsonArray();
            for (Game game : listGamesResult.games) {
                JsonObject jsonGame = new JsonObject();
                jsonGame.add("gameID", new JsonPrimitive(game.gameID));
                if (game.whiteUsername != null) {
                    jsonGame.add("whiteUsername", new JsonPrimitive(game.whiteUsername));
                } else {
                    jsonGame.add("whiteUsername", new JsonNull());
                }
                if (game.blackUsername != null) {
                    jsonGame.add("blackUsername", new JsonPrimitive(game.blackUsername));
                } else {
                    jsonGame.add("blackUsername", new JsonNull());
                }
                jsonGame.add("gameName", new JsonPrimitive(game.gameName));
                gameArray.add(jsonGame);
            }
            output.add("games", gameArray);
        }
        return output;
    }
}