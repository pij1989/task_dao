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
import java.util.Optional;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

    @Test
    public void testFindBookByPublishingHouse() {
        PublishingHouse publishingHouse = PublishingHouse.DMK;
        Book book1 = new Book(2L, "Effective Java", Set.of(new Author("Joshua", "Bloch")),
                PublishingHouse.DMK, 2013, 294, new BigDecimal(29.85, new MathContext(PRECISION)), Binding.SOFT);
        Book book2 = new Book(8L, "Java Persistence with Hibernate", Set.of(new Author("Christian", "Bauer"), new Author("Gavin", "King"), new Author("Gary", "Gregory")),
                PublishingHouse.DMK, 2018, 632, new BigDecimal(80.35, new MathContext(PRECISION)), Binding.SOLID);
        List<Book> actual = bookService.findBookByPublishingHouse(publishingHouse, BookComparator.AMOUNT_PAGE);
        List<Book> expect = List.of(book1, book2);
        assertEquals(actual, expect);
    }

    @Test
    public void testFindBookAfterYear() {
        int year = 2019;
        Book book1 = new Book(3L, "Java Concurrency in Practice", Set.of(new Author("Brian", "Goetz"), new Author("Tim", "Peierls"),
                new Author("Joshua", "Bloch"), new Author("Joseph", "Bowbeer"), new Author("David", "Holmes"), new Author("Doug", "Lea")),
                PublishingHouse.PITER, 2020, 464, new BigDecimal(54.68, new MathContext(PRECISION)), Binding.SOFT);
        Book book2 = new Book(5L, "Core Java. Volume 2 - Advanced Features", Set.of(new Author("Cay", "Horstmann")),
                PublishingHouse.VILIAMS, 2020, 864, new BigDecimal(70.55, new MathContext(PRECISION)), Binding.SOLID);
        List<Book> actual = bookService.findBookAfterYear(year, BookComparator.AMOUNT_PAGE);
        List<Book> expect = List.of(book1, book2);
        assertEquals(actual, expect);
    }

    @Test
    public void testFindById() {
        Book book = new Book(2L, "Effective Java", Set.of(new Author("Joshua", "Bloch")),
                PublishingHouse.DMK, 2013, 294, new BigDecimal(29.85, new MathContext(PRECISION)), Binding.SOFT);
        Optional<Book> actual = bookService.findById(2L);
        Optional<Book> expect = Optional.of(book);
        assertEquals(actual, expect);
    }

    @Test
    public void testDeleteById() {
        boolean actual = bookService.delete(2L);
        assertTrue(actual);
    }

    @Test
    public void testDeleteByBook() {
        Book book = new Book(2L, "Effective Java", Set.of(new Author("Joshua", "Bloch")),
                PublishingHouse.DMK, 2013, 294, new BigDecimal(29.85, new MathContext(PRECISION)), Binding.SOFT);
        boolean actual = bookService.delete(book);
        assertTrue(actual);
    }

    @Test
    public void testCreate() {
        Book book = new Book(11L, "Effective Java", Set.of(new Author("Joshua", "Bloch")),
                PublishingHouse.DMK, 2013, 294, new BigDecimal(29.85, new MathContext(PRECISION)), Binding.SOFT);
        boolean actual = bookService.create(book);
        assertTrue(actual);
    }

    @Test
    public void testUpdate() {
        Book book = new Book(2L, "Effective Java", Set.of(new Author("Joshua", "Bloch")),
                PublishingHouse.DMK, 2019, 394, new BigDecimal(39.85, new MathContext(PRECISION)), Binding.SOFT);
        Optional<Book> actual = bookService.update(book);
        Optional<Book> expect = Optional.of(book);
        assertEquals(actual, expect);
    }
}
