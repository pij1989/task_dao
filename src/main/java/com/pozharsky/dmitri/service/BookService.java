package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    List<Book> findAll(Comparator<Book> comparator);

    List<Book> findBookByAuthor(Author author, Comparator<Book> comparator);

    List<Book> findBookByPublishingHouse(PublishingHouse publishingHouse, Comparator<Book> comparator);

    List<Book> findBookAfterYear(int year, Comparator<Book> comparator);

    Optional<Book> findById(long id);

    boolean delete(long id);

    boolean delete(Book book);

    boolean create(Book book);

    Optional<Book> update(Book book);
}
