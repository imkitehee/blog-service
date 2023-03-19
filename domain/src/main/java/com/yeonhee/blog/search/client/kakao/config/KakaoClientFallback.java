package com.yeonhee.blog.search.client.kakao.config;


import com.yeonhee.blog.search.client.kakao.KakaoSearchClient;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogRequest;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.client.naver.NaverSearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class KakaoClientFallback implements FallbackFactory<KakaoSearchClient> {

    // TODO: hystrix
    @Override
    public KakaoSearchClient create(Throwable cause) {
        return new KakaoSearchClient() {
            @Override
            public KakaoSearchBlogResponse searchBlog(KakaoSearchBlogRequest request) {
                System.out.println("fallback 동작");
                return null;
            }
        };
    }
}
