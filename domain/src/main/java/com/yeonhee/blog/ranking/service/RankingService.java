package com.yeonhee.blog.ranking.service;

import com.yeonhee.blog.ranking.service.dto.RankingResponse;
import com.yeonhee.blog.type.RedisKeyType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RedisService redisService;

    private static final long RANKING_START_INDEX = 0;
    private static final long RANKING_END_INDEX = 9;
    private static final long RANKING_INCREMENT_SCORE = 1;
    private static final Duration WEEKLY_RANKING_TTL = Duration.ofDays(7);

    /**
     * 인기 검색어 목록 조회
     *
     * @return 랭킹 응답 객체 목록
     */
    public List<RankingResponse> getSearchWordRanking() {

        Set<ZSetOperations.TypedTuple<String>> result = redisService.zReverseRangeWithScores(RedisKeyType.RANKING_BY_SEARCH_WORD.getKey(),
                RANKING_START_INDEX, RANKING_END_INDEX);

        return result.stream()
                .map(r -> RankingResponse.of(r.getValue(), r.getScore()))
                .collect(Collectors.toList());
    }

    /**
     * 검색어의 검색횟수 누적
     *
     * @param query 검색어
     */
    public void incrementSearchWordScore(String query) {

        String key = RedisKeyType.RANKING_BY_SEARCH_WORD.getKey();

        Long ttl = redisService.getExpire(key);
        // ttl 설정이 없거나 key가 없는 경우
        if (ttl < 0) {
            redisService.zIncrementScore(key, query, RANKING_INCREMENT_SCORE);
            redisService.setExpire(key, WEEKLY_RANKING_TTL);
            return;
        }
        redisService.zIncrementScore(key, query, RANKING_INCREMENT_SCORE);
    }
}
