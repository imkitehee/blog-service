package com.yeonhee.blog.search.service;

import com.yeonhee.blog.search.client.kakao.KakaoSearchClient;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogRequest;
import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.client.naver.NaverSearchClient;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogRequest;
import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import com.yeonhee.blog.search.service.dto.SearchBlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchBlogService {

    private final KakaoSearchClient kakaoSearchClient;

    public SearchBlogResponse search(SearchBlogRequest request) {

        KakaoSearchBlogResponse kakaoBlogs = kakaoSearchClient.searchBlog(KakaoSearchBlogRequest.from(request));

        return SearchBlogResponse.fromKakao(kakaoBlogs);
     }
}
