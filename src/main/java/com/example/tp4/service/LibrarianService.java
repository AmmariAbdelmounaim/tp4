package com.example.tp4.service;

import com.example.tp4.dtos.BookDto;
import com.example.tp4.dtos.LoanDto;
import com.example.tp4.dtos.UserDto;
import com.example.tp4.entities.Book;
import com.example.tp4.entities.Loan;
import com.example.tp4.entities.User;
import com.example.tp4.repository.BookRepository;
import com.example.tp4.repository.LoanRepository;
import com.example.tp4.repository.UserRepository;
import com.example.tp4.utils.BookSpecifications;
import com.example.tp4.utils.UserSpecifications;
import com.example.tp4.utils.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibrarianService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

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

    public Loan loanBook(LoanDto loanDto) {
        // Check if the user already has 3 active loans
        User user = userRepository.findById(loanDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Book book = bookRepository.findById(loanDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setBorrower(user);
        bookRepository.save(book);
        long activeLoansCount = loanRepository.countByUser_IdAndIsActive(user.getId(), true);
        if (user.getBorrowingRestrictionEndDate() != null && user.getBorrowingRestrictionEndDate().isAfter(LocalDate.now())) {
            throw new IllegalStateException("User is under a borrowing restriction until " + user.getBorrowingRestrictionEndDate());
        }
        if (activeLoansCount >= 3) {
            throw new IllegalStateException("User already has 3 active loans.");
        }

        // Check if the book is not already loaned
        boolean isBookLoaned = loanRepository.existsByBook_IdAndIsActive(book.getId(), true);
        if (isBookLoaned) {
            throw new IllegalStateException("Book is already loaned.");
        }

        // Create a Loan object and set the properties from loanDto
        Loan loan = Loan.builder()
                .book(book)
                .user(user)
                .borrowDate(loanDto.getBorrowDate())
                .returnDate(loanDto.getBorrowDate().plusDays(15)) // Setting return date to 15 days from borrow date
                .isActive(true)
                .build();

        return loanRepository.save(loan);
    }
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        LocalDate today = LocalDate.now();
        User user = loan.getUser();
        // Check if the book is being returned late
        if (loan.getReturnDate().isBefore(today)) {
            LocalDate delayEnd = today.plusDays(ChronoUnit.DAYS.between(loan.getReturnDate(), today));
            user.setBorrowingRestrictionEndDate(delayEnd);
            userRepository.save(user);
        }


        // Set the borrower in the book entity to null
        Book book = loan.getBook();
        book.setBorrower(null);
        bookRepository.save(book);

        // delete the loan
        loanRepository.delete(loan);

    }
    public Loan extendLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        // Check if the loan is still active and has not been extended twice already
        if (!loan.getIsActive() || loan.getExtensionCount() >= 2) {
            throw new IllegalStateException("Loan cannot be extended further.");
        }

        // Extend the return date by 15 days
        loan.setReturnDate(loan.getReturnDate().plusDays(15));
        loan.setExtensionCount(loan.getExtensionCount() + 1);
        return loanRepository.save(loan);
    }

}
