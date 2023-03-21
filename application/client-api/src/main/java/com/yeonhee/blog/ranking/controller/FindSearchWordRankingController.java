package com.yeonhee.blog.ranking.controller;

import com.yeonhee.blog.ranking.controller.dto.FindSearchWordRankingResponse;
import com.yeonhee.blog.ranking.service.RankingService;
import com.yeonhee.blog.ranking.service.dto.RankingResponse;
import com.yeonhee.blog.response.ApiResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FindSearchWordRankingController {

    private final RankingService rankingService;

    @GetMapping("/v1/ranking/search-word")
    public ResponseEntity<ApiResponseModel<List<FindSearchWordRankingResponse>>> findSearchWordRanking() {

        List<RankingResponse> rankingResponses = rankingService.getSearchWordRanking();

        return ResponseEntity.ok()
                .body(ApiResponseModel.success(rankingResponses.stream()
                        .map(FindSearchWordRankingResponse::from)
                        .collect(Collectors.toList())));

    }
}
