package com.example.tp4.service;

import com.example.tp4.dtos.BookDto;
import com.example.tp4.dtos.UserDto;
import com.example.tp4.entities.Book;
import com.example.tp4.entities.User;
import com.example.tp4.repository.BookRepository;
import com.example.tp4.repository.UserRepository;
import com.example.tp4.utils.BookSpecifications;
import com.example.tp4.utils.UserSpecifications;
import com.example.tp4.utils.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;
import java.util.stream.Collectors;

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
    public void deleteBook(Long bookId){
        Book foundedBook = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(foundedBook);
    }

    public List<Book> getBooks( String title, String author, String isbn, String publisher, Integer publicationYear){
        Specification<Book> spec =Specification.where(null);
        if (title != null) {
            spec = spec.and(BookSpecifications.hasTitle(title));
        }
        if (author != null) {
            spec = spec.and(BookSpecifications.hasAuthor(author));
        }
        if (publisher != null) {
            spec = spec.and(BookSpecifications.hasPublisher(publisher));
        }
        if (isbn != null) {
            spec = spec.and(BookSpecifications.hasIsbn(isbn));
        }
        if (publicationYear != null) {
            spec = spec.and(BookSpecifications.hasPublicationYear(publicationYear));
        }
        return bookRepository.findAll(spec);
    }

    public List<User> getUsers(String name, String address, UserType userType){
        Specification<User> spec =Specification.where(null);
        if (name != null) {
            spec = spec.and(UserSpecifications.hasName(name));
        }
        if (address != null) {
            spec = spec.and(UserSpecifications.hasAddress(address));
        }
        if (userType != null) {
            spec = spec.and(UserSpecifications.hasUsertype(userType));
        }

        return userRepository.findAll(spec);
    }

    public User addUser(UserDto request){
        var newUser = User.builder()
                .name(request.getName())
                .userType(request.getUserType())
                .address(request.getAddress())
                .build();
        return userRepository.save(newUser);
    }
    public void deleteUser(Long userId){
        User foundedUser = userRepository.findById(userId).orElseThrow();
        userRepository.delete(foundedUser);
    }
}
