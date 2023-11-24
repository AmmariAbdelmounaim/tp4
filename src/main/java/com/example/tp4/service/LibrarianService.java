package com.example.tp4.service;

import com.example.tp4.dtos.BookDto;
import com.example.tp4.dtos.UserDto;
import com.example.tp4.entities.Book;
import com.example.tp4.entities.User;
import com.example.tp4.repository.BookRepository;
import com.example.tp4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Book addBook(BookDto request){
        var newbook = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .build();
        return bookRepository.save(newbook);
    }
    public Book deleteBook(Long bookId){
        Book foundedBook = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(foundedBook);
    }
    public User addUser(UserDto request){
        var newUser = User.builder()
                .name(request.getName())
                .userType(request.getUserType())
                .address(request.getAddress())
                .build();
    }
    public User deleteUser(Long userId){
        User foundedUser = userRepository.findById(userId).orElseThrow();

    }
}
