package com.yeonhee.blog.search.client.kakao.dto;

import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoSearchBlogRequest {

    private final String query;

    private final Integer page;

    private final Integer size;

    private final String sort;

    @Builder
    private KakaoSearchBlogRequest(String query, Integer page, Integer size, String sort) {
        this.query = query;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public static KakaoSearchBlogRequest from(SearchBlogRequest searchBlogCommand) {

        return KakaoSearchBlogRequest.builder()
                .query(searchBlogCommand.getQuery())
                .page(searchBlogCommand.getPage())
                .size(searchBlogCommand.getSize())
                .sort(searchBlogCommand.getSort().getInKakao())
                .build();
    }
}
