package com.example.jpa.repository;

import com.example.jpa.entities.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    Optional<Publisher> findById(Long id);
}
