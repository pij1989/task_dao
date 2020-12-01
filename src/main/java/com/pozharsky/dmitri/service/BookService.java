package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.exception.DaoException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    List<Book> findAll(Comparator<Book> comparator);

    Optional<Book> findById(long id);

    boolean delete(long id);

    boolean delete(Book entity);

    boolean create(Book entity) throws DaoException;

    Optional<Book> update(Book entity);
}
