package com.yeonhee.blog.ranking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
     * @return Set<ZSetOperations.TypedTuple < String>>
     * @see <a href="https://redis.io/commands/zrevrange">Redis Documentation: ZREVRANGE</a>
     */
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
        return zSetOperations.reverseRangeWithScores(key, start, end);
    }

    /**
     * TTL 조회
     *
     * @param key key
     * @return ttl(- 2 : 존재하지 않는 key, - 1 : ttl 없음)
     */
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * TTL 설정
     *
     * @param key key
     * @param ttl 설정할 ttl (second)
     * @return true: 성공, false: 실패
     */
    public Boolean setExpire(String key, long ttl) {
        return stringRedisTemplate.expire(key, Duration.ofSeconds(ttl));
    }

    /**
     * Key 삭제
     *
     * @param key key
     * @return true: 성공, false: 실패
     */
    public Boolean deleteKey(String key) {
        return stringRedisTemplate.delete(key);
    }
}
