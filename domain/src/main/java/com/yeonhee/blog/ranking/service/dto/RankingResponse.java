package com.yeonhee.blog.ranking.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class RankingResponse {

    private final String value;
    private final Double score;

    @Builder
    private RankingResponse(String value, Double score) {
        this.value = value;
        this.score = score;
    }

    public static RankingResponse from(ZSetOperations.TypedTuple<String> typedTuple) {

        return RankingResponse.builder()
                .value(typedTuple.getValue())
                .score(typedTuple.getScore())
                .build();
    }
}
