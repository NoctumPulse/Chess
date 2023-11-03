package Server.Models;

import java.util.Objects;

/**
 * Class that represents an authToken of the server.
 */
public class AuthToken {
    /**
     * field that contains the authToken of the user.
     */
    public String authToken;
    /**
     * field that contains the username of the user.
     */
    public String username;

    public AuthToken(String token, String name) {
        authToken = token;
        username = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof AuthToken)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            AuthToken testToken = (AuthToken) obj;
            if (!Objects.equals(this.authToken, testToken.authToken)) {
                return false;
            } else return Objects.equals(this.username, testToken.username);
        }
    }
}
