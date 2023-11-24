package com.example.tp4.controller;

import com.example.tp4.dtos.BookDto;
import com.example.tp4.dtos.UserDto;
import com.example.tp4.utils.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/librarian")
@RequiredArgsConstructor
public class LibrarianController {
    @PostMapping("/addBook")
    public void addBook(@RequestBody BookDto){

    }
    @DeleteMapping("/deleteBook")
    public ResponseEntity deleteBook(@RequestParam Long bookId){
        return ResponseEntity.ok();
    }
    @GetMapping("/getBooks")
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String isbn, @RequestParam String publisher, @RequestParam Integer publicationYear){

    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserDto){
        return ResponseEntity.ok();
    }
    @GetMapping("/getUser")
    public ResponseEntity<List<BookDto>> getUsers(@RequestParam(required = false) String nom, @RequestParam(required = false) UserType userType, @RequestParam(required = false) String address){
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestParam Long userId){
        return ResponseEntity;
    }

}
