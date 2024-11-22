package com.example.bookmanager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    // 생성자
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 특정 id로 Get
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable("id") Long id) {
        BookResponseDto bookResponseDto = bookService.getBookById(id);
        // Dto title이 not found면 404 통신 에러 전송
        if(bookResponseDto.getTitle().equals("Not Found")){
            return ResponseEntity.status(404).body(bookResponseDto);
        }
        return ResponseEntity.ok(bookResponseDto);
    }

    // 전체 조회 (페이지네이션 활용)
    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(Pageable pageable) {
        Page<BookResponseDto> books = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }

    // create
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.createBook(bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable("id") Long id, @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.updateBook(id, bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // 전체 출력 페이퍼
}
