package com.example.tp4.dtos;

import com.example.tp4.entities.Book;
import com.example.tp4.entities.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LoanDto {
    Long bookId;
    Long userId;
    Boolean isActive;
    LocalDate borrowDate;
    LocalDate returnDate;
}
