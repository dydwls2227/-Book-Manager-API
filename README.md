# 프로젝트 설명
이 프로젝트는 도서 정보를 관리하는 RESTful API 서버입니다.

# 실행 방법
1. H2 데이터베이스를 실행합니다.
2. 스프링 부트를 실행시킵니다.
3. 포스트맨을 통해 CURD를 실행해봅니다.

# API 문서
POST
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "9780134685991",
  "publishedDate": "2018-01-06"
}

GET
{
  "id": 1,
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "9780134685991",
  "publishedDate": "2018-01-06"
}

PUT
{
  "title": "Effective Java (Revised)",
  "author": "Joshua Bloch",
  "isbn": "9780134685991",
  "publishedDate": "2020-02-10"
}

DELETE
/api/books/1

# 유효성 검사 로직
BookRequestDto 클래스에서 유효성 검사를 구현 했습니다.
1. 책 제목 : 책 제목은 1-100자 이내
2. 작가 이름 : 이하동문
3. 출판 일자 : 출판일자는 현재 날짜보다 이전이어야 합니다.
4. ISBN : ISBN은 정규형 표현식으로 13자리 숫자이어야 합니다.

해당 조건에 맞지 않으면 IllegalArgumentException을 발생시킵니다.

# 조회 시 예외 처리 방식 설명
도서 조회시 해당 ID의 도서가 존재하지 않으면 IllegalArgumentException을 발생시키고
"해당 도서를 찾을 수 없습니다" 라는 메시지를 출력합니다.
