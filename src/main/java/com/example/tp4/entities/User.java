package com.example.tp4.entities;

import com.example.tp4.utils.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private UserType userType;
    @OneToMany(mappedBy = "borrower")
    private Set<Book> borrowedBooks;
    private LocalDate borrowingRestrictionEndDate;


}
