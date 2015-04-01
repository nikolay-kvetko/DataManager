package com.intetics.dao;

/**
 * Interface of CRUD operations
 */
public interface Dao<T> {

    /**
     * Save or update Entity in database
     * @param t
     */
    void saveOrUpdate(T t);

    /**
     * Get Entity from database
     * @param id
     * @return Entity
     */
    T get(Class aClass, Long id);

    /**
     * Delete Entity from database
     * @param t
     */
    void delete(T t);
}
