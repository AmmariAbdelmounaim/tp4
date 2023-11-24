package com.example.tp4.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer publicationYear;
}
