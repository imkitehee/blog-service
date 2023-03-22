package com.yeonhee.blog.search.service;

import com.yeonhee.blog.ClientApiTestApplication;
import com.yeonhee.blog.ranking.service.RedisService;
import com.yeonhee.blog.search.client.kakao.KakaoSearchClient;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import com.yeonhee.blog.search.service.dto.SearchBlogResponse;
import com.yeonhee.blog.type.SortType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@EnableFeignClients
@SpringBootTest(classes = ClientApiTestApplication.class)
class SearchBlogServiceTest {

    @Autowired
    RedisService redisService;

    @MockBean
    private KakaoSearchClient kakaoSearchClient;

    @Autowired
    private SearchBlogService searchBlogService;


    @Nested
    @DisplayName("블로그 검색 메서드는")
    class search {

        @Nested
        @DisplayName("카카오 API가 정상인 경우")
        class kakao_status_ok {

            @Test
            @DisplayName("카카오 API의 Response로 응답객체를 반환한다.")
            public void return_kakao_api_response() {

                KakaoSearchBlogResponse kakaoSearchBlogResponse = KakaoSearchBlogResponse.builder()
                        .meta(KakaoSearchBlogResponse.Meta.builder()
                                .totalCount(500)
                                .pageableCount(100)
                                .isEnd(false)
                                .build())
                        .documents(List.of(KakaoSearchBlogResponse.Document.builder()
                                .title("제목입니다.")
                                .contents("내용입니다.")
                                .blogName("kite-blog")
                                .url("http://kite-blog.com")
                                .datetime("2023-03-17T00:18:17.000+09:00")
                                .build()))
                        .build();
                // given
                given(kakaoSearchClient.searchBlog(any())).willReturn(kakaoSearchBlogResponse);

                // when
                SearchBlogResponse response = searchBlogService.search(SearchBlogRequest.of("검색어", 1, 10, SortType.ACCURACY));

                assertEquals(response.getPlatform(), "kakao");
            }
        }
    }
}
