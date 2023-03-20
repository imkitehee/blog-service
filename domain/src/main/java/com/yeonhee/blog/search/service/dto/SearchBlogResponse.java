package com.yeonhee.blog.search.service.dto;

import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogResponse;
import com.yeonhee.blog.util.DateTimeUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchBlogResponse {

    private final Page page;

    private final List<Result> results;

    private SearchBlogResponse(Page page, List<Result> results) {
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
        private String postDate;
    }

    public static SearchBlogResponse fromKakao(KakaoSearchBlogResponse response) {

        Page page = Page.builder()
                .totalCount(response.getMeta().getTotalCount())
                .pageableCount(response.getMeta().getPageableCount())
                .isEnd(response.getMeta().getIsEnd())
                .build();

        List<Result> results = response.getDocuments().stream()
                .map(document -> Result.builder()
                        .title(document.getTitle())
                        .contents(document.getContents())
                        .url(document.getUrl())
                        .blogName(document.getBlogName())
                        .postDate(OffsetDateTime.parse(document.getDatetime()).toLocalDateTime().format(DateTimeUtil.DATE_FORMAT_DASH))
                        .build())
                .collect(Collectors.toList());

        return new SearchBlogResponse(page, results);
    }

    public static SearchBlogResponse fromNaver(NaverSearchBlogResponse response) {

        boolean isEnd = Math.ceil(response.getTotal().doubleValue() / response.getDisplay().doubleValue()) > response.getStart();

        Page page = Page.builder()
                .totalCount(response.getTotal())
                .pageableCount(response.getDisplay())
                .isEnd(isEnd)
                .build();

        List<Result> results = response.getItems().stream()
                .map(document -> Result.builder()
                        .title(document.getTitle())
                        .contents(document.getDescription())
                        .url(document.getLink())
                        .blogName(document.getBloggerName())
                        .postDate(LocalDate.parse(document.getPostDate(), DateTimeUtil.DATE_FORMAT).format(DateTimeUtil.DATE_FORMAT_DASH))
                        .build())
                .collect(Collectors.toList());

        return new SearchBlogResponse(page, results);
    }
}
