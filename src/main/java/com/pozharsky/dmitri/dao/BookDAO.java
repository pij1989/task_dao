package com.pozharsky.dmitri.dao;

import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.exception.DAOException;
import com.pozharsky.dmitri.storage.BookWarehouse;

import java.util.List;

public class BookDAO implements AbstractDAO<Long, Book> {
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        for (int i = 0; i < bookWarehouse.size(); i++) {
            Book book = bookWarehouse.getBook(i);
            if (book.getId() == id) {
                return bookWarehouse.removeBook(book);
            }
        }
        throw new DAOException("Can not delete a book. This book doesn't exist");
    }

    @Override
    public boolean delete(Book entity) throws DAOException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        if (!bookWarehouse.contains(entity)) {
            throw new DAOException("Can not delete a book. This book doesn't exist");
        }
        return bookWarehouse.removeBook(entity);
    }

    @Override
    public boolean create(Book entity) throws DAOException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        if (bookWarehouse.contains(entity)) {
            throw new DAOException("Can not add a book. This book exists");
        }
        return bookWarehouse.addBook(entity);
    }

    @Override
    public Book update(Book entity) {
        return null;
    }
}
