package xyz.andrewkboyd.etltemplate.dao.interfaces;

/**
 * Interface defining the data access methods to the different stores
 */
public interface LatestNumbersDAO {
    /**
     * Get the latest number from the store
     * @return {int}
     */
    int getLatestNumber();

    /**
     * Add a new number to the store
     * @param number new test number
     */
    void insertNewNumber(int number);
}
