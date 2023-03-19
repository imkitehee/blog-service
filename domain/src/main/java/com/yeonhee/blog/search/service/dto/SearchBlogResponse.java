package com.yeonhee.blog.search.service.dto;

import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchBlogResponse {

    private Page page;

    private List<Result> results;

    @Builder
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

        public static Page of(Integer totalCount, Integer pageableCount, Boolean isEnd) {
            return Page.builder()
                    .totalCount(totalCount)
                    .pageableCount(pageableCount)
                    .isEnd(isEnd)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Result {

        private String title;
        private String contents;
        private String url;
        private String blogName;
        private LocalDateTime datetime;

        public static Result of(String title, String contents, String url, String blogName, LocalDateTime datetime) {
            return Result.builder()
                    .title(title)
                    .contents(contents)
                    .url(url)
                    .blogName(blogName)
                    .datetime(datetime)
                    .build();
        }
    }

    public static SearchBlogResponse fromKakao(KakaoSearchBlogResponse response) {
        return SearchBlogResponse.builder()
                .page(Page.of(response.getMeta().getTotalCount(), response.getMeta().getPageableCount(), response.getMeta().getIsEnd()))
                .results(response.getDocuments().stream()
                        .map(document ->
                                Result.of(document.getTitle(), document.getContents(), document.getUrl(), document.getBlogName(), document.getDatetime().toLocalDateTime()))
                        .collect(Collectors.toList()))
                .build();
    }

    public static SearchBlogResponse fromNaver(NaverSearchBlogResponse response) {

        boolean isEnd = Math.ceil(response.getTotal().doubleValue()/response.getDisplay().doubleValue()) > response.getStart();

        // TODO: datetime format
        return SearchBlogResponse.builder()
                .page(Page.of(response.getTotal(), response.getDisplay(), isEnd))
                .results(response.getItems().stream()
                        .map(document ->
                                Result.of(document.getTitle(), document.getDescription(), document.getLink(), document.getBloggerName(), LocalDateTime.now()))
                        .collect(Collectors.toList()))
                .build();
    }
}
