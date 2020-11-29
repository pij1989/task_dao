package com.pozharsky.dmitri.dao;

import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.exception.BookWarehouseException;
import com.pozharsky.dmitri.exception.DAOException;
import com.pozharsky.dmitri.storage.BookWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookDAO implements AbstractDAO<Long, Book> {
    private static final Logger logger = LogManager.getLogger(Book.class);

    @Override
    public List<Book> findAll() {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                books.add(bookWarehouse.getBook(i));
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
        }
        return books;
    }

    @Override
    public Book findEntityById(Long id) {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        Book book = null;
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book b = bookWarehouse.getBook(i);
                if (b.getId() == id) {
                    book = b;
                }
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
        }
        return book;
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book book = bookWarehouse.getBook(i);
                if (book.getId() == id) {
                    return bookWarehouse.removeBook(book);
                }
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
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
        BookWarehouse bookWarehouse = BookWarehouse.getInstance();
        Book book = null;
        try {
            for (int i = 0; i < bookWarehouse.size(); i++) {
                Book b = bookWarehouse.getBook(i);
                if (entity.getId() == b.getId()) {
                    bookWarehouse.setBook(i, entity);
                    book = entity;
                }
            }
        } catch (BookWarehouseException e) {
            logger.error("Incorrect index: " + e);
        }
        return book;
    }
}
