package com.example.tp4.repository;

import com.example.tp4.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Integer> {
    Optional<Loan> findById(Long id);
}
