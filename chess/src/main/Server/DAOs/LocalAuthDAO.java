package Server.DAOs;

import Server.Models.Game;
import dataAccess.DataAccessException;

import java.util.Set;

/**
 * Class for interacting with AuthToken Objects in Local Memory Storage
 */
public class LocalAuthDAO implements DAO {
    /**
     * Method for inserting a new AuthToken into storage.
     *
     * @param data New data to be inserted.
     * @throws DataAccessException Thrown if data is not of correct type.
     */
    @Override
    public void insert(Object data) throws DataAccessException {

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

    }

    /**
     * Method for removing a single AuthToken in storage.
     *
     * @param data Data to be removed.
     * @throws DataAccessException Thrown if data is not of the correct type or if that AuthToken does not exist in storage.
     */
    @Override
    public void remove(Object data) throws DataAccessException {

    }

    /**
     * Method for removing all AuthTokens in storage.
     *
     * @throws DataAccessException Thrown if data is not of the correct type or if there are no AuthTokens in storage.
     */
    @Override
    public void clear() throws DataAccessException {

    }
}
