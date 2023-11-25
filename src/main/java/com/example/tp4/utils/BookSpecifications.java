package com.example.tp4.utils;

import com.example.tp4.entities.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> hasTitle(String title){
        return (root, query, criteriaBuilder) ->
                title == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("title"), title);
    }
    public static Specification<Book> hasAuthor(String author){
        return (root, query, criteriaBuilder) ->
                author == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("author"), author);
    }
    public static Specification<Book> hasIsbn(String isbn){
        return (root, query, criteriaBuilder) ->
                isbn == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("isbn"), isbn);
    }
    public static Specification<Book> hasPublisher(String publisher){
        return (root, query, criteriaBuilder) ->
                publisher == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("publisher"), publisher);
    }
    public static Specification<Book> hasPublicationYear(Integer publicationYear){
        return (root, query, criteriaBuilder) ->
                publicationYear == null ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.equal(root.get("publicationYear"), publicationYear);
    }

}
