package com.yeonhee.blog.search.service.dto;

import com.yeonhee.blog.type.SortType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class SearchBlogRequest {

    private final String query;

    private final Integer page;

    private final Integer size;

    private final SortType sort;

    private SearchBlogRequest(String query, Integer page, Integer size, SortType sort) {
        this.query = query;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public static SearchBlogRequest of(@NonNull String query, Integer page, Integer size, SortType sort) {
        return new SearchBlogRequest(query, page, size, sort);
    }
}
