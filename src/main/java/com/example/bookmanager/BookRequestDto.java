package com.example.bookmanager;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookRequestDto {

    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;

    // 유효성 검증 구현
    public void validate() {
        if(title==null || title.length()<1 || title.length()>100) {
            throw new IllegalArgumentException("책 타이틀은 1-100자 안에 있어야합니다.");
        }
        if(author==null || author.length()<1 || author.length()>100) {
            throw new IllegalArgumentException("작가 이름은 1-100자 안에 있어야합니다.");
        }
        if(publishedDate==null || publishedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("출판일자는 지금 시각보다 적어야합니다.");
        }
        if (!isbn.matches("\\d{13}")) {
            throw new IllegalArgumentException("ISBN은 정규표현식 형태이어야만 합니다.");
        }
    }

}
