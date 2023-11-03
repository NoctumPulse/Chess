package Server.DAOs;

import Server.LocalDatabase;
import Server.Models.User;
import dataAccess.DataAccessException;

import java.util.Set;

/**
 * Class for interacting with User Objects in Local Memory Storage
 */
public class LocalUserDAO implements DAO {
    LocalDatabase database;
    public LocalUserDAO(Object storage){
        database = (LocalDatabase) storage;
    }
    /**
     * Method for inserting a new User into storage.
     *
     * @param data New data to be inserted.
     * @throws DataAccessException Thrown if data is not of correct type.
     */
    @Override
    public void insert(Object data) throws DataAccessException {
        User newUser = (User) data;
        database.users.put(newUser.username, newUser);
    }

    /**
     * Method for finding a single User in storage.
     *
     * @param data Data to be found.
     * @return User if found or null if not
     * @throws DataAccessException Thrown if data is not of the correct type or if that User does not exist in storage.
     */
    @Override
    public Object find(Object data) throws DataAccessException {
        return database.users.get((String) data);
    }

    /**
     * Method that returns all Users in storage.
     *
     * @return Set of all Users or null if none
     * @throws DataAccessException Thrown if no Users exist in storage.
     */
    @Override
    public Set<Object> getAll() throws DataAccessException {
        return null;
    }

    /**
     * Method for updating a single User in storage.
     *
     * @param data    Data that will be replaced.
     * @param newData Data that will replace old data
     * @throws DataAccessException Thrown if data is not of the correct type or if that User does not exist in storage.
     */
    @Override
    public void update(Object data, Object newData) throws DataAccessException {

    }

    /**
     * Method for removing a single User in storage.
     *
     * @param data Data to be removed.
     * @throws DataAccessException Thrown if data is not of the correct type or if that User does not exist in storage.
     */
    @Override
    public void remove(Object data) throws DataAccessException {

    }

    /**
     * Method for removing all Users in storage.
     *
     * @throws DataAccessException Thrown if data is not of the correct type or if there are no Users in storage.
     */
    @Override
    public void clear() throws DataAccessException {
        database.users.clear();
    }
}
