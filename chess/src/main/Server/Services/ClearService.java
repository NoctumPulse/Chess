package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalGameDAO;
import Server.DAOs.LocalUserDAO;
import Server.Results.ClearResult;
import dataAccess.DataAccessException;

/**
 * Class that acts as the intermediary
 * between the ClearHandler and the Data Access Objects (DAOs).
 */
public class ClearService {
    Object dataStorage;

    public ClearService(Object storage) {
        dataStorage = storage;
    }

    /**
     * Method that creates Data Access Objects for each data type in the Database and runs their clear commands.
     *
     * @return Returns a ClearResult object with its message set to any error encountered or null if no error occurred
     */
    public ClearResult clear() {
        try {
            LocalUserDAO userDAO = new LocalUserDAO(dataStorage);
            LocalGameDAO gameDAO = new LocalGameDAO(dataStorage);
            LocalAuthDAO authDAO = new LocalAuthDAO(dataStorage);
            userDAO.clear();
            gameDAO.clear();
            authDAO.clear();
            return new ClearResult();
        } catch (DataAccessException exception) {
            return new ClearResult(exception.getMessage());
        }
    }
}
