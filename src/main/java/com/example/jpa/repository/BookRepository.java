package com.example.jpa.repository;


import com.example.jpa.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book getOneByIsbn(String isbn);
}
