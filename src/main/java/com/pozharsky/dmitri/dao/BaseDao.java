package com.pozharsky.dmitri.dao;

import com.pozharsky.dmitri.exception.DaoException;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll() throws DaoException;

    T findById(long id) throws DaoException;

    boolean delete(long id) throws DaoException;

    boolean delete(T entity) throws DaoException;

    boolean create(T entity) throws DaoException;

    T update(T entity) throws DaoException;
}
