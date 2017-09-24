package com.foodkonnekt.service;

import java.util.List;

public interface Service<T> {

    /**
     * Saves the object, creates new record in the database.
     * 
     * @return status
     */
    boolean save(T object);

    /**
     * Get all the details of the given entity.
     */
    List<T> getAll();

    /**
     * Updates record.
     */
    T update(int id);

    /**
     * Deletes record.
     */
    void delete(int id);

    /**
     * Get all details of the given id
     * 
     * @param id
     * @return
     */
    List<T> findById(int id);

}
