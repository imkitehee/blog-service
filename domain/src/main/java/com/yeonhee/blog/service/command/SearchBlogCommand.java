package com.yeonhee.blog.service.command;

import com.yeonhee.blog.SortType;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class SearchBlogCommand {

    private final String query;

    private final Integer page;

    private final Integer size;

    private final SortType sort;

    private SearchBlogCommand(String query, Integer page, Integer size, SortType sort) {
        this.query = query;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public static SearchBlogCommand of(@NonNull String query, Integer page, Integer size, SortType sort) {
        return new SearchBlogCommand(query, page, size, sort);
    }
}
