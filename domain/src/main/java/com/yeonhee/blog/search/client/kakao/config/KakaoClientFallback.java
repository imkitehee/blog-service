package com.yeonhee.blog.search.client.kakao.config;


import com.yeonhee.blog.search.client.kakao.KakaoSearchClient;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogRequest;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.client.naver.NaverSearchClient;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogRequest;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogResponse;
import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import com.yeonhee.blog.type.SortType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClientFallback implements KakaoSearchClient {

    private final NaverSearchClient naverSearchClient;

    @Override
    public KakaoSearchBlogResponse searchBlog(KakaoSearchBlogRequest request) {
        log.info("fallback method call");

        SearchBlogRequest searchBlogRequest = SearchBlogRequest.of(request.getQuery(), request.getPage(), request.getSize(), SortType.findByInKakao(request.getSort()));

        // 네이버 검색 API 호출
        NaverSearchBlogResponse response = naverSearchClient.searchBlog(NaverSearchBlogRequest.from(searchBlogRequest));

       return KakaoSearchBlogResponse.fromNaver(response);
    }
}
