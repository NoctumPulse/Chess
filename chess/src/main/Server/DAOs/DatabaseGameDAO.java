package Server.DAOs;

import Server.Models.Game;
import dataAccess.DataAccessException;

import java.util.Set;

/**
 * Class for interacting with Game Objects in Database Storage
 */
public class DatabaseGameDAO implements DAO {
    /**
     * Method for inserting a new Game into storage.
     *
     * @param data New data to be inserted.
     * @throws DataAccessException Thrown if data is not of correct type.
     */
    @Override
    public void insert(Object data) throws DataAccessException {

    }

    /**
     * Method for finding a single Game in storage.
     *
     * @param data Data to be found.
     * @return Game if found or null if not
     * @throws DataAccessException Thrown if data is not of the correct type or if that Game does not exist in storage.
     */
    @Override
    public Object find(Object data) throws DataAccessException {
        return null;
    }

    /**
     * Method that returns all Games in storage.
     *
     * @return Set of all Games or null if none
     * @throws DataAccessException Thrown if no Games exist in storage.
     */
    @Override
    public Set<Object> getAll() throws DataAccessException {
        return null;
    }

    /**
     * Method for updating a single Game in storage.
     *
     * @param data    Data that will be replaced.
     * @param newData Data that will replace old data
     * @throws DataAccessException Thrown if data is not of the correct type or if that Game does not exist in storage.
     */
    @Override
    public void update(Object data, Object newData) throws DataAccessException {

    }

    /**
     * Method for removing a single Game in storage.
     *
     * @param data Data to be removed.
     * @throws DataAccessException Thrown if data is not of the correct type or if that Game does not exist in storage.
     */
    @Override
    public void remove(Object data) throws DataAccessException {

    }

    /**
     * Method for removing all Games in storage.
     *
     * @throws DataAccessException Thrown if data is not of the correct type or if there are no Games in storage.
     */
    @Override
    public void clear() throws DataAccessException {

    }
}
