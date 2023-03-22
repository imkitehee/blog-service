# blog-service
블로그 서비스 API를 제공합니다.

## 기술 스펙
- JAVA 11 
- Spring Boot 2.6
- Gradle 7.6 

## Modules 구조 및 의존성
```
blog-service
│  
├── application
│   └── client-api
│  
├── common
│ 
└── domain
```

## 실행 방법
1. blog-service.jar 파일을 다운로드 합니다.
2. blog-service.jar을 아래와 같이 실행합니다.
```bash
# jar 빌드

# RESTDocs 생성 

```

## API 명세
Blog Service API(http://localhost:8080/docs/index.html)
* ClientApiApplication 실행 후 확인 가능합니다.
```bash
#  

```

## 기능 소개
### 공통
- [ ] Exception Handling
  - ControllerAdvice를 통해 에러 메세지 커스텀
  - 에러 코드 Enum 정의
- [ ] 응답 메세지 규격 
  - ApiResponseModel 정의
### 블로그 검색
- [ ] 키워드를 통해 블로그를 검색한다.
- [ ] Sorting(정확도순, 최신순) 기능을 지원하고, 검색결과는 Pagination 형태로 제공한다.
- [ ] 기본적으로 [카카오 API의 블로그 검색](https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog)을 활용한다.
- [ ] 카카오 API 장애가 발생한 경우, [네이버 API의 블로그 검색](https://developers.naver.com/docs/serviceapi/search/blog/blog.md)을 활용한다.
  - Hystrix를 이용하여 장애 발생 시, Feign Client Fallback으로 처리 
- [ ] 검색 시 키워드를 Redis에 저장한다.
    - 검색 키워드 및 검색 횟수 제공 목적
    - Thread safe하도록 Redis 사용 
    - 트래픽과 데이터 양을 고려하여 TTL 설정
### 인기 검색어 목록
- [ ] 인기 검색어 TOP10을 내림차순으로 제공한다.
- [ ] 검색어 별로 검색된 횟수를 제공한다.
- [ ] 검색어 순위는 주 단위로(주간 랭킹) 제공한다.
