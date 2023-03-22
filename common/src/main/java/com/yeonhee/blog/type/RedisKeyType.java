package com.yeonhee.blog.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisKeyType {

    RANKING_BY_SEARCH_WORD("search_word_ranking", (long) (60 * 60 * 24 * 7));

    private final String key;
    private final Long ttl;
}
