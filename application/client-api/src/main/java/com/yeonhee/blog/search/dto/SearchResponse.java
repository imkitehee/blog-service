package com.yeonhee.blog.search.dto;

import com.yeonhee.blog.search.service.dto.SearchBlogResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchResponse {

    private final Page page;

    private final List<Result> results;

    private SearchResponse(Page page, List<Result> results) {
        this.page = page;
        this.results = results;
    }

    @Builder
    @Getter
    public static class Page {

        private Integer totalCount;

        private Integer pageableCount;

        private Boolean isEnd;
    }

    @Builder
    @Getter
    public static class Result {

        private String title;

        private String contents;

        private String url;

        private String blogName;

        private String thumbnail;

        private String postDate;
    }

    public static SearchResponse from(SearchBlogResponse response) {

        Page page = Page.builder()
                .totalCount(response.getPage().getTotalCount())
                .pageableCount(response.getPage().getPageableCount())
                .isEnd(response.getPage().getIsEnd())
                .build();

        List<Result> results = response.getResults().stream()
                .map(document -> Result.builder()
                        .title(document.getTitle())
                        .contents(document.getContents())
                        .url(document.getUrl())
                        .blogName(document.getBlogName())
                        .postDate(document.getPostDate())
                        .build())
                .collect(Collectors.toList());

        return new SearchResponse(page, results);
    }
}
