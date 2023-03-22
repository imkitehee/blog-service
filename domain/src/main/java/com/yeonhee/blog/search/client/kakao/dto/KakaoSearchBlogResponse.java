package com.yeonhee.blog.search.client.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeonhee.blog.search.client.naver.dto.NaverSearchBlogResponse;
import com.yeonhee.blog.util.DateTimeUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class KakaoSearchBlogResponse {

    private Meta meta;

    private List<Document> documents;

    @Builder.Default
    private String platform = "kakao";

    @Getter
    @Builder
    public static class Meta {

        /**
         * 검색된 문서 수
         */
        @JsonProperty("total_count")
        private Integer totalCount;

        /**
         * total_count 중 노출 가능 문서 수
         */
        @JsonProperty("pageable_count")
        private Integer pageableCount;

        /**
         * 마지막 페이지 여부
         */
        @JsonProperty("is_end")
        private Boolean isEnd;
    }

    @Getter
    @Builder
    public static class Document {

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
        @JsonProperty("blogname")
        private String blogName;

        /**
         * 검색 시스템에서 추출한 대표 미리보기 이미지 URL
         */
        private String thumbnail;

        /**
         * 블로그 글 작성시간
         * [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
         */
        private String datetime;
    }

    public static KakaoSearchBlogResponse fromNaver(NaverSearchBlogResponse response) {

        boolean isEnd = Math.ceil(response.getTotal().doubleValue() / response.getDisplay().doubleValue()) > response.getStart();

        Meta meta = Meta.builder()
                .totalCount(response.getTotal())
                .pageableCount(response.getDisplay())
                .isEnd(isEnd)
                .build();

        List<Document> documents = response.getItems().stream()
                .map(document -> Document.builder()
                        .title(document.getTitle())
                        .contents(document.getDescription())
                        .url(document.getLink())
                        .blogName(document.getBloggerName())
                        .datetime(LocalDate.parse(document.getPostDate(), DateTimeUtil.DATE_FORMAT).atStartOfDay()
                                .format(DateTimeUtil.OFFSET_DATE_TIME_FORMAT_DASH))
                        .build())
                .collect(Collectors.toList());

        return KakaoSearchBlogResponse.builder()
                .platform("naver")
                .meta(meta)
                .documents(documents)
                .build();
    }
}
