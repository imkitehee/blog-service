package com.yeonhee.blog.fixture;

import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.dto.SearchRequest;
import com.yeonhee.blog.search.service.dto.SearchBlogResponse;

import java.util.List;

public class _블로그_검색 {

    public static SearchBlogResponse getSearchBlogResponse() {
        KakaoSearchBlogResponse kakaoBlogs = KakaoSearchBlogResponse.builder()
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
        return SearchBlogResponse.fromKakao(kakaoBlogs);
    }

    public static SearchRequest getSearchRequest(String query) {
        SearchRequest request = new SearchRequest();
        request.setQuery(query);

        return request;
    }
}
