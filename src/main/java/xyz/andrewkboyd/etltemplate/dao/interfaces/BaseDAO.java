package xyz.andrewkboyd.etltemplate.dao.interfaces;

/**
 * Defines a base dao interface
 * @param <T>
 */
public interface BaseDAO<T> {
    /**
     * Save the object back to the data store
     * @param obj {T} object to save
     */
    void save(T obj);
}
