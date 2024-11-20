package com.example.bookmanager;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookRequestDto {

    // id 제외
    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;

    // 유효성 검증 구현
    // Post man 에서는 http 오류 발생, 스프링 부트(콘솔) 에서 출력되어짐
    public void validate() {
        if(title==null || title.length()<1 || title.length()>100) {
            throw new IllegalArgumentException("책 타이틀은 1-100자 안에 있어야합니다.");
        }
        if(author==null || author.length()<1 || author.length()>100) {
            throw new IllegalArgumentException("작가 이름은 1-100자 안에 있어야합니다.");
        }
        if(publishedDate==null || publishedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("출판일자는 지금 시각보다 적어야합니다.");
        }
        if (!isbn.matches("\\d{13}")) { // 정규표현식 무조건 13자리 이어야 한다.
            throw new IllegalArgumentException("ISBN은 정규표현식 형태이어야만 합니다.");
        }
    }

}
