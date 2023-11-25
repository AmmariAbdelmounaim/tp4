package com.example.tp4.repository;

import com.example.tp4.entities.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian,Long > {

}
