package Server;

import Server.Handlers.*;
import spark.Spark;

public class Server {
    LocalDatabase database;

    public Server() {
        database = new LocalDatabase();
        Spark.port(8000);
        Spark.externalStaticFileLocation("C:\\Users\\spenc\\Desktop\\C S 240\\Chess\\Chess_Project\\chess\\src\\web");
        Spark.delete("/db", ((request, response) -> (new ClearHandler(database)).handleRequest(request, response)));
        Spark.post("/user", ((request, response) -> (new RegisterHandler(database)).handleRequest(request, response)));
        Spark.post("/session", ((request, response) -> (new LoginHandler(database)).handleRequest(request, response)));
        Spark.delete("/session", ((request, response) -> (new LogoutHandler(database)).handleRequest(request, response)));
        Spark.get("/game", ((request, response) -> (new ListGamesHandler(database)).handleRequest(request, response)));
        Spark.post("/game", ((request, response) -> (new CreateGameHandler(database)).handleRequest(request, response)));
        Spark.put("/game", ((request, response) -> (new JoinGameHandler(database)).handleRequest(request, response)));
        Spark.awaitInitialization();
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
