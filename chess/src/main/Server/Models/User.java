package Server.Models;

import java.util.Objects;

/**
 * Class that represents a server user.
 */
public class User {
    /**
     * field that contains the username of the user.
     */
    public String username;
    /**
     * field that contains the password of the user.
     */
    public String password;
    /**
     * field that contains the email of the user.
     */
    public String email;

    public User(String user, String pass, String address) {
        username = user;
        password = pass;
        email = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof User)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            User testUser = (User) obj;
            if (!Objects.equals(this.username, testUser.username)) {
                return false;
            } else if (!Objects.equals(this.password, testUser.password)) {
                return false;
            } else return Objects.equals(this.email, testUser.email);
        }
    }
}
