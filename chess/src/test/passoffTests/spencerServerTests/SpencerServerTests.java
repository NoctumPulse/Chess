package passoffTests.spencerServerTests;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalGameDAO;
import Server.DAOs.LocalUserDAO;
import Server.LocalDatabase;
import Server.Requests.*;
import Server.Results.*;
import Server.Services.*;
import chess.ChessGame;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;

public class SpencerServerTests {
    LocalDatabase database = new LocalDatabase();
    LocalUserDAO testUserDAO = new LocalUserDAO(database);
    LocalAuthDAO testAuthDAO = new LocalAuthDAO(database);
    LocalGameDAO testGameDAO = new LocalGameDAO(database);

    @BeforeEach
    public void clearDatabase() {
        try {
            testUserDAO.clear();
            testAuthDAO.clear();
            testGameDAO.clear();
        } catch (DataAccessException e) {
            System.out.println("ERROR");
        }

    }

    @Test
    @Order(1)
    @DisplayName("Register New User")
    public void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        Assertions.assertEquals(200, registerResult.resultCode, "Result code was not 200");
        Assertions.assertEquals("testUser", registerResult.username, "Username is not testUser");
        Assertions.assertNotNull(registerResult.authToken, "No authToken returned");
    }

    @Test
    @Order(2)
    @DisplayName("Register User Twice")
    public void registerTwice() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        registerRequest.username = "testUser";
        registerRequest.password = "newPassword";
        registerRequest.email = "new Email";
        registerResult = registerService.register(registerRequest);
        Assertions.assertEquals(403, registerResult.resultCode, "Result code was not 403");
        Assertions.assertEquals("Error: already taken", registerResult.message, "Message is not correct");
        Assertions.assertNull(registerResult.authToken, "authToken is not null");
    }

    @Test
    @Order(3)
    @DisplayName("Bad Register Request")
    public void badRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = null;
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        Assertions.assertEquals(400, registerResult.resultCode, "Result code was not 400");
        Assertions.assertEquals("Error: bad request", registerResult.message, "Message is not correct");
        Assertions.assertNull(registerResult.authToken, "authToken is not null");
    }

    @Test
    @Order(4)
    @DisplayName("Login User")
    public void login() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        registerService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = "testUser";
        loginRequest.password = "password123";
        LoginService loginService = new LoginService(database);
        LoginResult loginResult = loginService.login(loginRequest);
        Assertions.assertEquals(200, loginResult.resultCode, "Result code was not 200");
        Assertions.assertEquals(loginRequest.username, loginResult.username, "wrong user was logged in");
        Assertions.assertNotNull(loginResult.authToken, "authToken was not returned");
    }

    @Test
    @Order(5)
    @DisplayName("Login Nonexistent User")
    public void loginNonexistent() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = "testUser";
        loginRequest.password = "password123";
        LoginService loginService = new LoginService(database);
        LoginResult loginResult = loginService.login(loginRequest);
        Assertions.assertEquals(401, loginResult.resultCode, "Result code was not 401");
        Assertions.assertNull(loginResult.username, "Nonexistent user was logged in");
        Assertions.assertNull(loginResult.authToken, "authToken was created for nonexistent user");
        Assertions.assertEquals("Error: unauthorized", loginResult.message, "Incorrect error message");
    }

    @Test
    @Order(6)
    @DisplayName("Logout User")
    public void logout() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        registerService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = "testUser";
        loginRequest.password = "password123";
        LoginService loginService = new LoginService(database);
        LoginResult loginResult = loginService.login(loginRequest);
        String existingAuth = loginResult.authToken;
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.authorization = existingAuth;
        LogoutService logoutService = new LogoutService(database);
        LogoutResult logoutResult = logoutService.logout(logoutRequest);
        Assertions.assertEquals(200, logoutResult.resultCode, "Result code was not 200");
        Assertions.assertNull(logoutResult.message, "error message was not null");
    }

    @Test
    @Order(7)
    @DisplayName("Logout No Auth")
    public void logoutNoAuth() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        registerService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = "testUser";
        loginRequest.password = "password123";
        LoginService loginService = new LoginService(database);
        loginService.login(loginRequest);
        String existingAuth = "incorrect";
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.authorization = existingAuth;
        LogoutService logoutService = new LogoutService(database);
        LogoutResult logoutResult = logoutService.logout(logoutRequest);
        Assertions.assertEquals(401, logoutResult.resultCode, "Result code was not 401");
        Assertions.assertEquals("Error: unauthorized", logoutResult.message, "Incorrect error message");
    }

    @Test
    @Order(8)
    @DisplayName("Create Game")
    public void createGame() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest, existingAuth);
        Assertions.assertEquals(200, createGameResult.resultCode, "Result code was not 200");
        Assertions.assertNotEquals(-1, createGameResult.gameID, "No game ID was given to game");
    }

    @Test
    @Order(9)
    @DisplayName("Create Game No Auth")
    public void createGameNoAuth() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        registerService.register(registerRequest);
        String existingAuth = "incorrect";
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest, existingAuth);
        Assertions.assertEquals(401, createGameResult.resultCode, "Result code was not 401");
        Assertions.assertEquals(-1, createGameResult.gameID, "Game ID was given to game when it should not have been");
        Assertions.assertEquals("Error: unauthorized", createGameResult.message, "Incorrect error message was returned");
    }

    @Test
    @Order(10)
    @DisplayName("Create Game Bad Request")
    public void createGameBadRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = null;
        CreateGameService createGameService = new CreateGameService(database);
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest, existingAuth);
        Assertions.assertEquals(400, createGameResult.resultCode, "Result code was not 400");
        Assertions.assertEquals(-1, createGameResult.gameID, "Game ID was given to game when it should not have been");
        Assertions.assertEquals("Error: bad request", createGameResult.message, "Incorrect error message was returned");
    }

    @Test
    @Order(11)
    @DisplayName("Join Game")
    public void joinGame() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest, existingAuth);
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.gameID = createGameResult.gameID;
        joinGameRequest.playerColor = ChessGame.TeamColor.WHITE;
        JoinGameService joinGameService = new JoinGameService(database);
        JoinGameResult joinGameResult = joinGameService.joinGame(joinGameRequest, existingAuth);
        Assertions.assertEquals(200, joinGameResult.resultCode, "Result code was not 200");
        Assertions.assertNull(joinGameResult.message, "Error message is not null");
    }

    @Test
    @Order(12)
    @DisplayName("Join Game Bad Request")
    public void joinGameBadRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        createGameService.createGame(createGameRequest, existingAuth);
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.gameID = -1;
        joinGameRequest.playerColor = ChessGame.TeamColor.WHITE;
        JoinGameService joinGameService = new JoinGameService(database);
        JoinGameResult joinGameResult = joinGameService.joinGame(joinGameRequest, existingAuth);
        Assertions.assertEquals(400, joinGameResult.resultCode, "Result code was not 400");
        Assertions.assertEquals("Error: bad request", joinGameResult.message, "Error message is not correct");
    }

    @Test
    @Order(13)
    @DisplayName("Join Game No Auth")
    public void joinGameNoAuth() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest, existingAuth);
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.gameID = createGameResult.gameID;
        joinGameRequest.playerColor = ChessGame.TeamColor.WHITE;
        JoinGameService joinGameService = new JoinGameService(database);
        JoinGameResult joinGameResult = joinGameService.joinGame(joinGameRequest, "incorrect");
        Assertions.assertEquals(401, joinGameResult.resultCode, "Result code was not 401");
        Assertions.assertEquals("Error: unauthorized", joinGameResult.message, "Error message is not correct");
    }

    @Test
    @Order(14)
    @DisplayName("Join Game Already Taken")
    public void joinGameAlreadyTaken() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.username = "testUser2";
        registerRequest2.password = "password123";
        registerRequest2.email = "test2@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        RegisterResult registerResult2 = registerService.register(registerRequest2);
        String existingAuth2 = registerResult2.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest, existingAuth);
        JoinGameRequest joinGameRequest = new JoinGameRequest();
        joinGameRequest.gameID = createGameResult.gameID;
        joinGameRequest.playerColor = ChessGame.TeamColor.WHITE;
        JoinGameService joinGameService = new JoinGameService(database);
        joinGameService.joinGame(joinGameRequest, existingAuth);
        JoinGameResult joinGameResult2 = joinGameService.joinGame(joinGameRequest, existingAuth2);
        Assertions.assertEquals(403, joinGameResult2.resultCode, "Result code was not 403");
        Assertions.assertEquals("Error: already taken", joinGameResult2.message, "Error message is not correct");
    }

    @Test
    @Order(15)
    @DisplayName("List Games")
    public void listGames() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        createGameService.createGame(createGameRequest, existingAuth);
        ListGamesRequest listGamesRequest = new ListGamesRequest();
        listGamesRequest.authorization = existingAuth;
        ListGamesService listGamesService = new ListGamesService(database);
        ListGamesResult listGamesResult = listGamesService.listGames(listGamesRequest, existingAuth);
        Assertions.assertEquals(200, listGamesResult.resultCode, "Result code was not 200");
        Assertions.assertNull(listGamesResult.message, "Error message is not null");
        Assertions.assertEquals(1, listGamesResult.games.size(), "Set of Game objects size is incorrect");
    }

    @Test
    @Order(16)
    @DisplayName("List Games No Auth")
    public void listGamesNoAuth() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.username = "testUser";
        registerRequest.password = "password123";
        registerRequest.email = "test@test.com";
        RegisterService registerService = new RegisterService(database);
        RegisterResult registerResult = registerService.register(registerRequest);
        String existingAuth = registerResult.authToken;
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.gameName = "testGame";
        CreateGameService createGameService = new CreateGameService(database);
        createGameService.createGame(createGameRequest, existingAuth);
        ListGamesRequest listGamesRequest = new ListGamesRequest();
        listGamesRequest.authorization = existingAuth;
        ListGamesService listGamesService = new ListGamesService(database);
        ListGamesResult listGamesResult = listGamesService.listGames(listGamesRequest, "incorrect");
        Assertions.assertEquals(401, listGamesResult.resultCode, "Result code was not 401");
        Assertions.assertNull(listGamesResult.games, "Games set object is not null");
        Assertions.assertEquals("Error: unauthorized", listGamesResult.message, "Error message is incorrect");
    }

    @Test
    @Order(17)
    @DisplayName("Clear Data")
    public void clear() {
        ClearService clearService = new ClearService(database);
        ClearResult clearResult = clearService.clear();
        Assertions.assertEquals(200, clearResult.resultCode, "Result code was not 200");
        Assertions.assertEquals(0, database.games.size(), "Database games not cleared");
        Assertions.assertEquals(0, database.users.size(), "Database users not cleared");
        Assertions.assertEquals(0, database.authTokens.size(), "Database authTokens not cleared");
    }
}
