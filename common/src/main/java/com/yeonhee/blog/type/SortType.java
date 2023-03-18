package com.yeonhee.blog.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {

    ACCURACY("정확도순", "accuracy", "sim"),
    RECENCY("최신순", "recency", "date");

    private final String description;
    private final String inKakao;
    private final String inNaver;
}
