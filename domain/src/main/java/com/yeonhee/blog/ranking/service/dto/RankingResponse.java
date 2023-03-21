package com.yeonhee.blog.ranking.service.dto;

import lombok.Getter;

@Getter
public class RankingResponse {

    private final String value;
    private final Double score;

    private RankingResponse(String value, Double score) {
        this.value = value;
        this.score = score;
    }

    public static RankingResponse of(String value, Double score) {

        return new RankingResponse(value, score);
    }
}
