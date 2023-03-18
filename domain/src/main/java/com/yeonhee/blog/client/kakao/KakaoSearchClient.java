package com.yeonhee.blog.client.kakao;


import com.yeonhee.blog.client.kakao.config.KakaoClientConfiguration;
import com.yeonhee.blog.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.service.command.SearchBlogCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakao-api", url = "${feign.kakao-api.url}", configuration = KakaoClientConfiguration.class)
public interface KakaoSearchClient {

    @GetMapping("/v2/search/blog")
    KakaoSearchBlogResponse searchBlog(@SpringQueryMap SearchBlogCommand command);
}
