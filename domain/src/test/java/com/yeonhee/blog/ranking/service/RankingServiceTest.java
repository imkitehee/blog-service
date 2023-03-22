package com.yeonhee.blog.ranking.service;

import com.yeonhee.blog.ClientApiTestApplication;
import com.yeonhee.blog.type.RedisKeyType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import redis.embedded.RedisServer;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = ClientApiTestApplication.class)
class RankingServiceTest {

    @Autowired
    RedisService redisService;

    @Autowired
    RankingService rankingService;

    private static RedisServer redisServer;

    @BeforeAll
    public static void startRedis() {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterAll
    public static void stopRedis() {
        redisServer.stop();
    }

    @Nested
    @DisplayName("검색어 누적 메서드는")
    class incrementSearchWordScore {

        @Nested
        @DisplayName("TTL 설정이 없거나 key가 없는 경우")
        class has_no_expire {
            RedisKeyType redisKeyType = RedisKeyType.RANKING_BY_SEARCH_WORD;

            @BeforeEach
            public void before() {
                // given
                redisService.deleteKey(redisKeyType.getKey());
            }

            @Test
            @DisplayName("해당 key에 대한 TTL을 설정하고 검색어를 누적한다.")
            public void increase_score_with_ttl() {
                // when
                rankingService.incrementSearchWordScore("검색어1");

                // then
                Long ttl = redisService.getExpire(redisKeyType.getKey());

                assertTrue(ttl > 0 && ttl <= redisKeyType.getTtl());
            }
        }

        @Nested
        @DisplayName("TTL이 있는 경우")
        class has_ttl {

            RedisKeyType redisKeyType = RedisKeyType.RANKING_BY_SEARCH_WORD;
            long originalTTL = 1000;

            @BeforeEach
            public void before() {
                // given
                redisService.zIncrementScore(redisKeyType.getKey(), "검색어1", 10);
                redisService.setExpire(redisKeyType.getKey(), originalTTL);
            }

            @Test
            @DisplayName("TTL 변경없이 검색어를 누적한다.")
            public void increase_score_only() {

                // when
                rankingService.incrementSearchWordScore("검색어2");

                // then
                Long ttl = redisService.getExpire(redisKeyType.getKey());

                assertTrue(ttl <= originalTTL);
            }
        }
    }
}
