package com.yeonhee.blog.search.dto;

import com.yeonhee.blog.search.client.kakao.dto.KakaoSearchBlogResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class SearchResponse {

    private Page page;

    private List<Result> results;

    @Builder
    @Getter
    public static class Page {

        /**
         * 검색된 문서 수
         */
        private Integer totalCount;

        /**
         * total_count 중 노출 가능 문서 수
         */
        private Integer pageableCount;

        /**
         * 마지막 페이지 여부
         */
        private Boolean isEnd;
    }

    @Builder
    @Getter
    public static class Result {

        /**
         * 블로그 글 제목
         */
        private String title;

        /**
         * 블로그 글 요약
         */
        private String contents;

        /**
         * 블로그 글 URL
         */
        private String url;

        /**
         * 블로그의 이름
         */
        private String blogName;

        /**
         * 대표 미리보기 이미지 URL
         */
        private String thumbnail;

        /**
         * 블로그 글 작성시간
         */
        private LocalDateTime datetime;
    }

    public static SearchResponse fromKakao(KakaoSearchBlogResponse response) {

        KakaoSearchBlogResponse.Meta meta = response.getMeta();
        List<KakaoSearchBlogResponse.Document> documents = response.getDocuments();

        return SearchResponse.builder()
                .page(Page.builder()
                        .totalCount(meta.getTotalCount())
                        .pageableCount(meta.getPageableCount())
                        .isEnd(meta.getIsEnd())
                        .build())
                .results(documents.stream()
                        .map(document -> Result.builder()
                                .title(document.getTitle())
                                .contents(document.getContents())
                                .url(document.getUrl())
                                .blogName(document.getBlogName())
                                .thumbnail(document.getThumbnail())
                                .datetime(document.getDatetime().toLocalDateTime())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }
}
