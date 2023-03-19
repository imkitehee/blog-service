package com.yeonhee.blog.ranking.controller.dto;

import com.yeonhee.blog.ranking.service.dto.RankingResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindSearchWordRankingResponse {

    private String query;
    private long score;

    @Builder
    private FindSearchWordRankingResponse(String query, long score) {
        this.query = query;
        this.score = score;
    }

    public static FindSearchWordRankingResponse from(RankingResponse response) {
        return FindSearchWordRankingResponse.builder()
                .query(response.getValue())
                .score(response.getScore().longValue())
                .build();
    }
}
