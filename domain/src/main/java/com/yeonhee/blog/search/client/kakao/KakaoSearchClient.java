package com.yeonhee.blog.search.client.kakao;


import com.yeonhee.blog.search.client.kakao.config.KakaoClientConfiguration;
import com.yeonhee.blog.search.client.kakao.config.KakaoClientFallback;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogRequest;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakao-api", url = "${feign.kakao-api.url}", configuration = KakaoClientConfiguration.class, fallback = KakaoClientFallback.class)
public interface KakaoSearchClient {

    @GetMapping("/v2/search/blog")
    KakaoSearchBlogResponse searchBlog(@SpringQueryMap KakaoSearchBlogRequest request);
}
