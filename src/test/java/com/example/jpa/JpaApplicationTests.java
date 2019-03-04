package com.example.jpa;

import com.example.jpa.entities.Author;
import com.example.jpa.entities.Book;
import com.example.jpa.entities.Publisher;
import com.example.jpa.repository.AuthorRepository;
import com.example.jpa.repository.BookRepository;
import com.example.jpa.service.BookManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

	@Autowired
	private BookManager bookManager;


	@Test
	public void contextLoads() {
		bookManager.getBooks().forEach(System.out::println);
	}



}

