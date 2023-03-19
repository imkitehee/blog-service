package com.yeonhee.blog.search.client.naver;


import com.yeonhee.blog.search.client.naver.config.NaverClientConfiguration;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogRequest;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "naver-api", url = "${feign.naver-api.url}", configuration = NaverClientConfiguration.class)
public interface NaverSearchClient {

    @GetMapping("/v1/search/blog.json")
    NaverSearchBlogResponse searchBlog(@SpringQueryMap NaverSearchBlogRequest request);
}
