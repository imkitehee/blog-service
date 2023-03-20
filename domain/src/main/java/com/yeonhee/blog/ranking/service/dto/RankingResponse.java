package com.yeonhee.blog.ranking.service.dto;

import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class RankingResponse {

    private final String value;
    private final Double score;

    private RankingResponse(String value, Double score) {
        this.value = value;
        this.score = score;
    }

    public static RankingResponse from(ZSetOperations.TypedTuple<String> typedTuple) {

        return new RankingResponse(typedTuple.getValue(), typedTuple.getScore());
    }
}
