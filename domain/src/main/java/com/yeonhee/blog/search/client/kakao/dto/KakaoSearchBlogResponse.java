package com.yeonhee.blog.search.client.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
public class KakaoSearchBlogResponse {

    private Meta meta;

    private List<Document> documents;

    @Getter
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
}
