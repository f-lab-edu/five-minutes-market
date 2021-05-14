# five-minutes-market (오분 마켓)

- 누구나 손쉽게 구매 및 판매 가능한 오픈 마켓입니다.
- 다양한 상품 데이터롤 적재하기 위해 상품 등록은 Open API를 사용해 DB에 등록 예정입니다.

## 기술스택

- JAVA 11
- Spring Boot 2.4.5 
- MySQL
- MyBatis
- Gradle

## Wiki

- [기능정의](https://github.com/f-lab-edu/five-minutes-market/wiki/%EC%98%A4%EB%B6%84%EB%A7%88%EC%BC%93-%EA%B8%B0%EB%8A%A5-%EC%A0%95%EC%9D%98)

## 브랜치 관리 전략(GitHub flow)

`master`를 항상 최신 상태로 만들며, stable 상태로 Product에 배포되는 브랜치 (`master` = `main`)

- 작업시 브랜치 생성
    - ex) feature/작업명
- 작업 내용에 대해 Pull Request 생성
- `main`으로 머지