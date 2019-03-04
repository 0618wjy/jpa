package com.example.jpa.service.impl;


import com.example.jpa.entities.Author;
import com.example.jpa.entities.Book;
import com.example.jpa.entities.Publisher;
import com.example.jpa.repository.AuthorRepository;
import com.example.jpa.repository.BookRepository;
import com.example.jpa.repository.PublisherRepository;
import com.example.jpa.service.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBookManager implements BookManager {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Override
    @Transactional
    public List<Author> getAuthors() {
        return this.toList(this.authorRepository.findAll());
    }

    @Override
    @Transactional
    public List<Book> getBooks() {
        return this.toList(this.bookRepository.findAll());
    }

    @Override
    @Transactional
    public List<Publisher> getPublishers() {
        return this.toList(this.publisherRepository.findAll());
    }

    private <E> List<E> toList(Iterable<E> i) {
        List<E> list = new ArrayList<>();
        i.forEach(list::add);
        return list;
    }

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        if (author.getId() < 1)
            this.authorRepository.save(author);
//        else
//            //this.authorRepository.(author);
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        if (book.getId() < 1)
            this.bookRepository.save(book);
//        else
//            this.bookRepository.update(book);
    }

    @Override
    @Transactional
    public void savePublisher(Publisher publisher) {
        if (publisher.getId() < 1)
            this.publisherRepository.save(publisher);
//        else
//            this.publisherRepository.update(publisher);
    }
}
