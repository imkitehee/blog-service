package com.yeonhee.blog.service;

import com.yeonhee.blog.client.kakao.KakaoSearchClient;
import com.yeonhee.blog.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.service.command.SearchBlogCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchBlogService implements SearchBlogUseCase {

    private final KakaoSearchClient kakaoSearchClient;

    @Override
    public KakaoSearchBlogResponse search(SearchBlogCommand searchBlogCommand) {

        return kakaoSearchClient.searchBlog(searchBlogCommand);
    }
}
