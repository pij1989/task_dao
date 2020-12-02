package com.pozharsky.dmitri.service.impl;

import com.pozharsky.dmitri.dao.impl.BookDaoImpl;
import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;
import com.pozharsky.dmitri.exception.DaoException;
import com.pozharsky.dmitri.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl bookService;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (bookService == null) {
            bookService = new BookServiceImpl();
        }
        return bookService;
    }

    @Override
    public List<Book> findAll() {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = bookDao.findAll();
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findAll(Comparator<Book> comparator) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = bookDao.findAll();
            books.sort(comparator);
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findBookByAuthor(Author author, Comparator<Book> comparator) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = bookDao.findAll().stream()
                    .filter(b -> {
                        boolean flag = false;
                        Set<Author> authors = b.getAuthors();
                        for (Author a : authors) {
                            if (a.equals(author)) {
                                flag = true;
                            }
                        }
                        return flag;
                    })
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findBookByPublishingHouse(PublishingHouse publishingHouse, Comparator<Book> comparator) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = bookDao.findAll().stream()
                    .filter(b -> b.getPublishingHouse().equals(publishingHouse))
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public List<Book> findBookAfterYear(int year, Comparator<Book> comparator) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = bookDao.findAll().stream()
                    .filter(b -> b.getYear() > year)
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            logger.error("Can not find books: " + e);
        }
        return books;
    }

    @Override
    public Optional<Book> findById(long id) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        Book book = null;
        try {
            book = bookDao.findById(id);
        } catch (DaoException e) {
            logger.error("Can not find book: " + e);
        }
        return book == null ? Optional.empty() : Optional.of(book);
    }

    @Override
    public boolean delete(long id) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        boolean isDelete = false;
        try {
            isDelete = bookDao.delete(id);
        } catch (DaoException e) {
            logger.error("Can not delete a book: " + e);
        }
        return isDelete;
    }

    @Override
    public boolean delete(Book book) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        boolean isDelete = false;
        try {
            isDelete = bookDao.delete(book);
        } catch (DaoException e) {
            logger.error("Can not delete a book: " + e);
        }
        return isDelete;
    }

    @Override
    public boolean create(Book book) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        boolean isCreate = false;
        try {
            isCreate = bookDao.create(book);
        } catch (DaoException e) {
            logger.error("Can not create a book: " + e);
        }
        return isCreate;
    }

    @Override
    public Optional<Book> update(Book book) {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        Book b = null;
        try {
            b = bookDao.update(book);
        } catch (DaoException e) {
            logger.error("Can not update a book: " + e);
        }
        return b == null ? Optional.empty() : Optional.of(book);
    }
}
