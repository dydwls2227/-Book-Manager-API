package com.example.bookmanager;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor

public class BookService {
    private final BookRepository bookRepository;

    // 도서등록
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        
        // BookRequestDto 에서 만든 유효성검사 메소드를 사용하여, 등록하기 전 검사를 하고, 검사에 걸리면 오류 발생
        bookRequestDto.validate();
        
        // 빌더 패턴으로 Book 클래스의 객체 생성
        Book book = Book.builder()
                .title(bookRequestDto.getTitle())
                .author(bookRequestDto.getAuthor())
                .isbn(bookRequestDto.getIsbn())
                .publishedDate(bookRequestDto.getPublishedDate())
                .build();

        Book savedBook = bookRepository.save(book);
        
        // Dto 변환 후 리턴
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
        // orElse 메소드는 해당 값이 null 이든 아니든 관계없이 항상 불린다.
        //orElseGet 메소드는 해당 값이 null 일 때만 불린다.
        Book book = bookRepository.findById(id).orElseGet(()-> ErrorBook(id));
        
        // Dto 변환후 리턴
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .build();
    }

    public Book ErrorBook(Long id) {
        Book errorBook = new Book();
        errorBook.setId((long) -1);
        errorBook.setTitle("Not Found");
        errorBook.setAuthor("Unknown");
        errorBook.setIsbn("N/A");
        errorBook.setPublishedDate(LocalDate.now());
        return errorBook;
    }
    
    // 페이지네이션을 활용한 전체 출력
    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(book -> BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .build());
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
