package com.yeonhee.blog.search.client.naver.dto;

import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NaverSearchBlogRequest {

    private final String query;

    private final Integer page;

    private final Integer display;

    private final String sort;

    private NaverSearchBlogRequest(String query, Integer page, Integer display, String sort) {
        this.query = query;
        this.page = page;
        this.display = display;
        this.sort = sort;
    }

    public static NaverSearchBlogRequest from(SearchBlogRequest searchBlogCommand) {

        return new NaverSearchBlogRequest(searchBlogCommand.getQuery(), searchBlogCommand.getPage(),
                searchBlogCommand.getSize(), searchBlogCommand.getSort().getInNaver());
    }
}
