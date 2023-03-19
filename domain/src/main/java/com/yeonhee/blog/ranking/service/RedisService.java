package com.yeonhee.blog.ranking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * SortedSet - score만큼 증가
     *
     * @param key   key
     * @param value value
     * @param score 증가시킬 값
     * @see <a href="https://redis.io/commands/zincrby">Redis Documentation: ZINCRBY</a>
     */
    public void zIncrementScore(String key, String value, long score) {
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
        zSetOperations.incrementScore(key, value, score);
    }

    /**
     * SortedSet - score의 내림차순으로 range(start~end)만큼 조회
     *
     * @param key   key
     * @param start 시작 index
     * @param end   마지막 index
     * @return Set<ZSetOperations.TypedTuple <String>>
     * @see <a href="https://redis.io/commands/zrevrange">Redis Documentation: ZREVRANGE</a>
     */
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
        return zSetOperations.reverseRangeWithScores(key, start, end);
    }
}
