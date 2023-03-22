package com.yeonhee.blog.search.client.naver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverSearchBlogResponse {

    /**
     * 총 검색 결과 개수
     */
    private Integer total;

    /**
     * 검색 시작 위치
     */
    private Integer start;

    /**
     * 한 번에 표시할 검색 결과 개수
     */
    private Integer display;

    /**
     * 검색 결과를 생성한 시간
     */
    private String lastBuildDate;

    /**
     * 블로그 목록
     */
    private List<Item> items;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {

        /**
         * 블로그 글 제목
         */
        private String title;

        /**
         * 블로그 글 요약
         */
        private String description;

        /**
         * 블로그 글 URL
         */
        private String link;

        /**
         * 블로그의 이름
         */
        @JsonProperty("bloggername")
        private String bloggerName;

        /**
         * 블로그 주소
         */
        @JsonProperty("bloggerlink")
        private String bloggerLink;

        /**
         * 블로그 글 작성시간
         */
        @JsonProperty("postdate")
        private String postDate;
    }
}
