package com.pozharsky.dmitri.service.impl;

import com.pozharsky.dmitri.comparator.BookComparator;
import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Binding;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;
import com.pozharsky.dmitri.reader.BookReader;
import com.pozharsky.dmitri.storage.BookWarehouse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class BookServiceImplTest {
    static final String FILE = "data\\books.txt";
    static final int PRECISION = 4;
    BookReader bookReader;
    BookWarehouse bookWarehouse;
    BookServiceImpl bookService;
    List<Book> books;

    @BeforeMethod
    public void setUp() {
        bookReader = new BookReader();
        books = bookReader.readBooks(FILE);
        bookWarehouse = BookWarehouse.getInstance();
        bookWarehouse.setBooks(books);
        bookService = BookServiceImpl.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        bookReader = null;
        bookWarehouse = null;
        bookService = null;
        books = null;
    }

    @Test
    public void testFindAll() {
        List<Book> actual = bookService.findAll();
        List<Book> expect = books;
        assertEquals(actual, expect);
    }

    @Test
    public void testFindAllWithComparator() {
        List<Book> actual = bookService.findAll(BookComparator.AMOUNT_PAGE);
        List<Book> expect = books;
        expect.sort(BookComparator.AMOUNT_PAGE);
        assertEquals(actual, expect);
    }

    @Test
    public void testFindBookByAuthor() {
        Author author = new Author("Bruce", "Eckel");
        Book book = new Book(1L, "Thinking in Java", Set.of(new Author("Bruce", "Eckel")),
                PublishingHouse.PITER, 2019, 1168, new BigDecimal(79.65, new MathContext(PRECISION)), Binding.SOLID);
        List<Book> actual = bookService.findBookByAuthor(author, BookComparator.AMOUNT_PAGE);
        List<Book> expect = List.of(book);
        assertEquals(actual, expect);
    }

    @Test(enabled = false)
    public void testFindBookByPublishingHouse() {
    }

    @Test(enabled = false)
    public void testFindBookAfterYear() {
    }

    @Test(enabled = false)
    public void testFindById() {
    }

    @Test(enabled = false)
    public void testDelete() {
    }

    @Test(enabled = false)
    public void testTestDelete() {
    }

    @Test(enabled = false)
    public void testCreate() {
    }

    @Test(enabled = false)
    public void testUpdate() {
    }
}