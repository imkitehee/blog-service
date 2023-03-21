package com.yeonhee.blog.fixture;

import com.yeonhee.blog.ranking.service.dto.RankingResponse;

import java.util.List;

public class _랭킹_조회 {

    public static List<RankingResponse> getRankingList() {
        return List.of(RankingResponse.of("1위검색어", 30.0),
                RankingResponse.of("2위검색어", 20.0),
                RankingResponse.of("3위검색어", 10.0),
                RankingResponse.of("4위검색어", 10.0),
                RankingResponse.of("5위검색어", 9.0),
                RankingResponse.of("6위검색어", 9.0),
                RankingResponse.of("7위검색어", 8.0),
                RankingResponse.of("8위검색어", 7.0),
                RankingResponse.of("9위검색어", 6.0),
                RankingResponse.of("10위검색어", 5.0)
        );
    }
}
