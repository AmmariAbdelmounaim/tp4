package com.example.tp4.controller;

import com.example.tp4.dtos.BookDto;
import com.example.tp4.dtos.LoanDto;
import com.example.tp4.dtos.UserDto;
import com.example.tp4.entities.Book;
import com.example.tp4.entities.Loan;
import com.example.tp4.entities.User;
import com.example.tp4.service.LibrarianService;
import com.example.tp4.utils.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/librarian")
@RequiredArgsConstructor
public class LibrarianController {
    private final LibrarianService service;
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(service.addBook(bookDto));
    }
    @DeleteMapping("/deleteBook")
    public void deleteBook(@RequestParam Long bookId){
        service.deleteBook(bookId);
    }
    @GetMapping("/getBooks")
        public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String isbn, @RequestParam(required = false) String publisher, @RequestParam(required = false) Integer publicationYear){
        return ResponseEntity.ok(service.getBooks(title, author, isbn, publisher, publicationYear));
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.addUser(userDto));
    }
    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String nom, @RequestParam(required = false) UserType userType, @RequestParam(required = false) String address){
        return ResponseEntity.ok(service.getUsers(nom,address,userType));
    }
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam Long userId){
        service.deleteUser(userId);
    }

    @PostMapping("/loanBook")
    public ResponseEntity<Loan> loanBook(@RequestBody LoanDto loanDto){
        return ResponseEntity.ok(service.loanBook(loanDto));
    }
    @PostMapping("/returnBook")
    public void returnBook(@RequestParam Long loanId) {
        service.returnBook(loanId);
    }
    @PostMapping("/extendLoan")
    public ResponseEntity<Loan> extendLoan(@RequestParam Long loanId) {
        Loan loan = service.extendLoan(loanId);
        return ResponseEntity.ok(loan);
    }

}
