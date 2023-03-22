package com.yeonhee.blog.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SortType {

    ACCURACY("정확도순", "accuracy", "sim"),
    RECENCY("최신순", "recency", "date");

    private final String description;
    private final String inKakao;
    private final String inNaver;

    public static SortType findByInKakao(String inKakao) {
        return Arrays.stream(SortType.values())
                .filter(sortType -> sortType.getInKakao().equals(inKakao))
                .findFirst()
                .orElse(SortType.ACCURACY);
    }
}
