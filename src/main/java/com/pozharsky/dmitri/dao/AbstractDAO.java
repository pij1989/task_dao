package com.pozharsky.dmitri.dao;

import com.pozharsky.dmitri.exception.DAOException;

import java.util.List;

public interface AbstractDAO<K, T> {
    List<T> findAll();

    T findEntityById(K id);

    boolean deleteById(K id) throws DAOException;

    boolean delete(T entity) throws DAOException;

    boolean create(T entity) throws DAOException;

    T update(T entity);
}
