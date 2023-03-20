package com.yeonhee.blog.ranking.controller.dto;

import com.yeonhee.blog.ranking.service.dto.RankingResponse;
import lombok.Getter;

@Getter
public class FindSearchWordRankingResponse {

    private final String query;
    private final long score;

    private FindSearchWordRankingResponse(String query, long score) {
        this.query = query;
        this.score = score;
    }

    public static FindSearchWordRankingResponse from(RankingResponse response) {
        return new FindSearchWordRankingResponse(response.getValue(), response.getScore().longValue());
    }
}
