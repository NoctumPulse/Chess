package Server.DAOs;

import Server.LocalDatabase;
import Server.Models.Game;
import dataAccess.DataAccessException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for interacting with Game Objects in Local Memory Storage
 */
public class LocalGameDAO implements DAO {
    LocalDatabase database;
    public LocalGameDAO(Object storage){
        database = (LocalDatabase) storage;
    }
    public int getNextGameID(){
        return database.nextGameID;
    }
    /**
     * Method for inserting a new Game into storage.
     *
     * @param data New data to be inserted.
     * @throws DataAccessException if data is not of correct type.
     */
    @Override
    public void insert(Object data) throws DataAccessException {
        if(!(data instanceof Game)){
            throw new DataAccessException("Error: cannot add a non game object to the data storage");
        }
        else{
            database.games.put(database.nextGameID, (Game) data);
            database.nextGameID += 1;
        }
    }
    /**
     * Method for finding a single Game in storage.
     *
     * @param data Data to be found.
     * @return Game if found or null if not
     * @throws DataAccessException if data is not of the correct type or if that Game does not exist in storage.
     */
    @Override
    public Object find(Object data) throws DataAccessException {
        return database.games.get((int) data);
    }

    /**
     * Method that returns all Games in storage.
     *
     * @return Set of all Games or null if none
     * @throws DataAccessException if no Games exist in storage.
     */
    @Override
    public Set<Object> getAll() throws DataAccessException {
        Set<Object> games = new HashSet<>();
        for(Map.Entry<Integer, Game> entry: database.games.entrySet()){
            games.add(entry.getValue());
        }
        return games;
    }

    /**
     * Method for updating a single Game in storage.
     *
     * @param data    Data that will be replaced.
     * @param newData Data that will replace old data
     * @throws DataAccessException if data is not of the correct type or if that Game does not exist in storage.
     */
    @Override
    public void update(Object data, Object newData) throws DataAccessException {
        database.games.replace((int) data, (Game) newData);
    }

    /**
     * Method for removing a single Game in storage.
     *
     * @param data Data to be removed.
     * @throws DataAccessException if data is not of the correct type or if that Game does not exist in storage.
     */
    @Override
    public void remove(Object data) throws DataAccessException {

    }

    /**
     * Method for removing all Games in storage.
     *
     * @throws DataAccessException if there are no Games in storage.
     */
    @Override
    public void clear() throws DataAccessException {
        database.games.clear();
    }
}
