package Server.DAOs;

import Server.LocalDatabase;
import Server.Models.AuthToken;
import dataAccess.DataAccessException;

import java.util.Map;
import java.util.Set;

/**
 * Class for interacting with AuthToken Objects in Local Memory Storage
 */
public class LocalAuthDAO implements DAO {
    LocalDatabase database;

    public LocalAuthDAO(Object storage) {
        database = (LocalDatabase) storage;
    }

    /**
     * Method for inserting a new AuthToken into storage.
     *
     * @param data New data to be inserted.
     * @throws DataAccessException Thrown if data is not of correct type.
     */
    @Override
    public void insert(Object data) throws DataAccessException {
        database.authTokens.put(((AuthToken) data).username, (AuthToken) data);
    }

    /**
     * Method for finding a single AuthToken in storage.
     *
     * @param data Data to be found.
     * @return AuthToken if found or null if not
     * @throws DataAccessException Thrown if data is not of the correct type or if that AuthToken does not exist in storage.
     */
    @Override
    public Object find(Object data) throws DataAccessException {
        for (Map.Entry<String, AuthToken> entry : database.authTokens.entrySet()) {
            AuthToken testToken = entry.getValue();
            if (testToken.authToken != null) {
                if (testToken.authToken.equals(data)) {
                    return testToken;
                }
            }
        }
        return null;
    }

    /**
     * Method that returns all AuthTokens in storage.
     *
     * @return Set of all AuthTokens or null if none
     * @throws DataAccessException Thrown if no AuthTokens exist in storage.
     */
    @Override
    public Set<Object> getAll() throws DataAccessException {
        return null;
    }

    /**
     * Method for updating a single AuthToken in storage.
     *
     * @param data    Data that will be replaced.
     * @param newData Data that will replace old data
     * @throws DataAccessException Thrown if data is not of the correct type or if that AuthToken does not exist in storage.
     */
    @Override
    public void update(Object data, Object newData) throws DataAccessException {
        database.authTokens.replace((String) data, (AuthToken) newData);
    }

    /**
     * Method for removing a single AuthToken in storage.
     *
     * @param data Data to be removed.
     * @throws DataAccessException Thrown if data is not of the correct type or if that AuthToken does not exist in storage.
     */
    @Override
    public void remove(Object data) throws DataAccessException {
        database.authTokens.remove((String) data);
    }

    /**
     * Method for removing all AuthTokens in storage.
     *
     * @throws DataAccessException if there are no AuthTokens in storage.
     */
    @Override
    public void clear() throws DataAccessException {
        database.authTokens.clear();
    }
}
