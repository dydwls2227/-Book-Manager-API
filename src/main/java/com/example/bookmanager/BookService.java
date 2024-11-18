package com.example.bookmanager;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BookService {
    private final BookRepository bookRepository;

    // 도서등록
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = Book.builder()
                .title(bookRequestDto.getTitle())
                .author(bookRequestDto.getAuthor())
                .isbn(bookRequestDto.getIsbn())
                .publishedDate(bookRequestDto.getPublishedDate())
                .build();
        Book savedBook = bookRepository.save(book);

        return BookResponseDto.builder()
                .id(savedBook.getId())
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .isbn(savedBook.getIsbn())
                .publishedDate(savedBook.getPublishedDate())
                .build();
    }

    // 도서조회
    public BookResponseDto getBookById(Long id) {
        // +예외처리
        Book book = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));

        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .build();
    }

    // 도서 수정
    public BookResponseDto updateBook(Long id,BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));

        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPublishedDate(bookRequestDto.getPublishedDate());
        Book updateBook = bookRepository.save(book);

        return BookResponseDto.builder()
                .id(updateBook.getId())
                .title(updateBook.getTitle())
                .author(updateBook.getAuthor())
                .isbn(updateBook.getIsbn())
                .publishedDate(updateBook.getPublishedDate())
                .build();
    }

    // 도서 삭제
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));
        bookRepository.delete(book);
    }

}
