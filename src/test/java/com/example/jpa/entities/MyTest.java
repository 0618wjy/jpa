package com.example.jpa.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


public class MyTest {

    private Random random;
    private EntityManagerFactory factory;

    @Before
    public void before() {
        try {
            this.random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

        factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    @After
    public void after() {
        factory.close();
    }
    @Test
    public void save() {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = factory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();

            Publisher publisher = new Publisher();
            publisher.setName("John Wiley & Sons");
            publisher.setAddress("1234 Baker Street");
            publisher.setDateFounded(new GregorianCalendar());
            manager.persist(publisher);

            Author author = new Author();
            author.setName("Nicholas S. Williams");
            author.setEmailAddress("nick@example.com");
            author.setGender(Gender.MALE);
            manager.persist(author);

            Book book = new Book();
            book.setIsbn("" + this.random.nextInt(Integer.MAX_VALUE));
            book.setTitle("Professional Java for Web Applications");
            book.setAuthor("Nicholas S. Williams");
            book.setPublisher("John Wiley & Sons");
            book.setPrice(59.99D);
            book.setPreviewPdf("abc".getBytes());
            manager.persist(book);


            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
    }

    @Test
    public void get() {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = this.factory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();

            Publisher publisher = manager.find(Publisher.class, 1);
            System.out.println(publisher);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
    }

    @Test
    public void query() {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = this.factory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();

            CriteriaBuilder builder = manager.getCriteriaBuilder();

            CriteriaQuery<Publisher> q1 = builder.createQuery(Publisher.class);
            List<Publisher> publishers = manager.createQuery(q1.select(q1.from(Publisher.class))).getResultList();

            CriteriaQuery<Author> q2 = builder.createQuery(Author.class);
            List<Author> authors = manager.createQuery(q2.select(q2.from(Author.class))).getResultList();

            CriteriaQuery<Book> q3 = builder.createQuery(Book.class);
            List<Book> books = manager.createQuery(q3.select(q3.from(Book.class))).getResultList();

            transaction.commit();

            publishers.forEach(System.out::println);
            authors.forEach(System.out::println);
            books.forEach(System.out::println);

        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
    }

}