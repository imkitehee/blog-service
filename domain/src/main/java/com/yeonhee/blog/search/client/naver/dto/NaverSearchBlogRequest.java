package com.yeonhee.blog.search.client.naver.dto;

import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import lombok.Getter;

@Getter
public class NaverSearchBlogRequest {

    private final String query;

    private final Integer start;

    private final Integer display;

    private final String sort;

    private NaverSearchBlogRequest(String query, Integer start, Integer display, String sort) {
        this.query = query;
        this.start = start;
        this.display = display;
        this.sort = sort;
    }

    public static NaverSearchBlogRequest from(SearchBlogRequest request) {

        return new NaverSearchBlogRequest(request.getQuery(), request.getPage(), request.getSize(), request.getSort().getInNaver());
    }
}
