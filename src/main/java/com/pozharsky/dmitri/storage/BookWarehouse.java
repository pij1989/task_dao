package com.pozharsky.dmitri.storage;

import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.exception.BookWarehouseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookWarehouse {
    private static BookWarehouse warehouse;
    private List<Book> books;

    private BookWarehouse() {
        this.books = new ArrayList<>();
    }

    public static BookWarehouse getInstance() {
        if (warehouse == null) {
            warehouse = new BookWarehouse();
        }
        return warehouse;
    }

    public boolean addBook(Book book) {
        Book newBook = cloneBook(book);
        return books.add(newBook);
    }

    public Book getBook(int index) throws BookWarehouseException {
        if (index < 0 || index >= books.size()) {
            throw new BookWarehouseException("Incorrect index. It should be more than zero and less than size");
        }
        Book book = books.get(index);
        return cloneBook(book);
    }

    public Book setBook(int index, Book book) throws BookWarehouseException {
        if (index < 0 || index >= books.size()) {
            throw new BookWarehouseException("Incorrect index. It should be more than zero and less than size");
        }
        Book newBook = cloneBook(book);
        return books.set(index, newBook);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public int size() {
        return books.size();
    }

    public boolean contains(Book book) {
        return books.contains(book);
    }

    public List<Book> getBooks() {
        return books.stream()
                .map(this::cloneBook)
                .collect(Collectors.toList());
    }

    public void setBooks(List<Book> books) {
        this.books = books.stream()
                .map(this::cloneBook)
                .collect(Collectors.toList());
    }

    private Book cloneBook(Book book) {
        Book newBook = new Book();
        newBook.setId(book.getId());
        newBook.setName(book.getName());
        Set<Author> authors = book.getAuthors().stream()
                .map(e -> new Author(e.getFirstName(), e.getLastName()))
                .collect(Collectors.toSet());
        newBook.setAuthors(authors);
        newBook.setPublishingHouse(book.getPublishingHouse());
        newBook.setYear(book.getYear());
        newBook.setAmountPage(book.getAmountPage());
        newBook.setPrice(book.getPrice());
        newBook.setBinding(book.getBinding());
        return newBook;
    }
}
