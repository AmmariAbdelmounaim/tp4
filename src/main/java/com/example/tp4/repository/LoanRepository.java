package com.example.tp4.repository;

import com.example.tp4.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
    Optional<Loan> findById(Long id);
    long countByUser_IdAndIsActive(Long userId, Boolean isActive);
    boolean existsByBook_IdAndIsActive(Long bookId, Boolean isActive);

}
