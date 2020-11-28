package com.pozharsky.dmitri.reader;

import com.pozharsky.dmitri.entity.Author;
import com.pozharsky.dmitri.entity.Binding;
import com.pozharsky.dmitri.entity.Book;
import com.pozharsky.dmitri.entity.PublishingHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookReader {
    private static final Logger logger = LogManager.getLogger(BookReader.class);
    private static final String REG_COMMA = ",";
    private static final String REG_SEMICOLON = ";";
    private static final String REG_SPACE = " ";
    private static final String DEFAULT_FILE = "data\\books.txt";
    private static final int EMPTY = 0;

    public List<Book> readBooks(String file) {
        if (file == null) {
            file = DEFAULT_FILE;
        }
        File f = new File(file);
        if (f.length() == EMPTY) {
            f = new File(DEFAULT_FILE);
        }
        String line;
        List<Book> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            while ((line = reader.readLine()) != null) {
                Book book = parseBook(line);
                list.add(book);
            }
        } catch (FileNotFoundException e) {
            logger.fatal("File is not found: " + e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.fatal("Error of file: " + e);
            throw new RuntimeException(e);
        }
        return list;
    }

    private Book parseBook(String line) {
        String[] arrayString = line.split(REG_SEMICOLON);
        long id = Long.parseLong(arrayString[0].trim());
        String name = arrayString[1].trim();
        Set<Author> authors = parseAuthorSet(arrayString[2].trim());
        PublishingHouse publishingHome = PublishingHouse.valueOf(arrayString[3].trim().toUpperCase());
        int year = Integer.parseInt(arrayString[4].trim());
        int amountPage = Integer.parseInt(arrayString[5].trim());
        double price = Double.parseDouble(arrayString[6].trim());
        Binding binding = Binding.valueOf(arrayString[7].trim().toUpperCase());
        return new Book(id, name, authors, publishingHome, year, amountPage, price, binding);
    }

    private Set<Author> parseAuthorSet(String line) {
        Set<Author> authors = new HashSet<>();
        String[] arrayAuthor = line.split(REG_COMMA);
        for (String author : arrayAuthor) {
            String[] array = author.split(REG_SPACE);
            String firstName = array[0].trim();
            String lastName = array[1].trim();
            authors.add(new Author(firstName, lastName));
        }
        return authors;
    }
}
