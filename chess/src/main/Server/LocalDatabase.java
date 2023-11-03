package Server;

import Server.Models.AuthToken;
import Server.Models.Game;
import Server.Models.User;

import java.util.HashMap;

public class LocalDatabase {
    public HashMap<Integer, Game> games;
    public HashMap<String, User> users;
    public HashMap<String, AuthToken> authTokens;
    public int nextGameID;

    public LocalDatabase() {
        nextGameID = 1;
        games = new HashMap<>();
        users = new HashMap<>();
        authTokens = new HashMap<>();
    }
}
