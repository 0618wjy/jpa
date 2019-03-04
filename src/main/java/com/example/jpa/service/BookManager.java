package com.example.jpa.service;

import com.example.jpa.entities.Author;
import com.example.jpa.entities.Book;
import com.example.jpa.entities.Publisher;

import java.util.List;

public interface BookManager {
    List<Author> getAuthors();

    List<Book> getBooks();

    List<Publisher> getPublishers();

    void saveAuthor(Author author);

    void saveBook(Book book);

    void savePublisher(Publisher publisher);
}
