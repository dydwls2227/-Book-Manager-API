package com.example.bookmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable("id") Long id) {
        BookResponseDto bookResponseDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookResponseDto);
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.createBook(bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable("id") Long id, @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.updateBook(id, bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // 전체 출력 페이퍼
}