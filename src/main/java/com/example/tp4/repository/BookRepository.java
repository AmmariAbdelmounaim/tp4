package com.example.tp4.repository;

import com.example.tp4.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>, JpaSpecificationExecutor<Book> {
    Optional<Book> findById(Long id);
}
