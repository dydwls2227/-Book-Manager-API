package com.example.bookmanager;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;

    public BookResponseDto(Long id, String title, String author, String isbn, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
    }

}
