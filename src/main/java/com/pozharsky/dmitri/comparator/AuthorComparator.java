package com.pozharsky.dmitri.comparator;

import com.pozharsky.dmitri.entity.Author;

import java.util.Comparator;

public enum AuthorComparator implements Comparator<Author> {
    FIRST_NAME {
        @Override
        public int compare(Author o1, Author o2) {
            String firstName1 = o1.getFirstName();
            String firstName2 = o2.getFirstName();
            return firstName1.compareTo(firstName2);
        }
    },
    LAST_NAME {
        @Override
        public int compare(Author o1, Author o2) {
            String lastName1 = o1.getLastName();
            String lastName2 = o2.getLastName();
            return lastName1.compareTo(lastName2);
        }
    }
}
