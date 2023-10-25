package Server.DAOs;

import Server.Models.Game;
import dataAccess.DataAccessException;

import java.util.Set;

/**
 * Interface class that defines methods for accessing and modifying data.
 */
public interface DAO {
    /**
     * Method for inserting new data into storage.
     *
     * @param data New data to be inserted.
     * @throws DataAccessException Thrown if data is not of correct type.
     */
    public void insert(Object data) throws DataAccessException;

    /**
     * Method for finding a single data object in storage.
     *
     * @param data Data to be found.
     * @throws DataAccessException Thrown if data is not of the correct type or if it does not exist in storage.
     */
    public Object find(Object data) throws DataAccessException;

    /**
     * Method that returns all data objects in storage.
     *
     * @throws DataAccessException Thrown if no data exists in storage.
     */
    public Set<Object> getAll() throws DataAccessException;

    /**
     * Method for updating a single data object in storage.
     *
     * @param data    Data that will be replaced.
     * @param newData Data that will replace old data
     * @throws DataAccessException Thrown if data is not of the correct type or if it does not exist in storage.
     */
    public void update(Object data, Object newData) throws DataAccessException;

    /**
     * Method for removing a single data object in storage.
     *
     * @param data Data to be removed.
     * @throws DataAccessException Thrown if data is not of the correct type or if it does not exist in storage.
     */
    public void remove(Object data) throws DataAccessException;

    /**
     * Method for removing all data in storage.
     *
     * @throws DataAccessException Thrown if data is not of the correct type or if there is no data in storage.
     */
    public void clear() throws DataAccessException;
}
