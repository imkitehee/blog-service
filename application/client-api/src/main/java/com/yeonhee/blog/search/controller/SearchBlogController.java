package com.yeonhee.blog.search.controller;


import com.yeonhee.blog.ranking.service.RankingService;
import com.yeonhee.blog.search.dto.SearchRequest;
import com.yeonhee.blog.response.ApiResponseModel;
import com.yeonhee.blog.search.dto.SearchResponse;
import com.yeonhee.blog.search.service.SearchBlogService;
import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SearchBlogController {

    private final SearchBlogService searchBlogService;
    private final RankingService rankingService;

    @GetMapping("/v1/search/blog")
    public ResponseEntity<ApiResponseModel<SearchResponse>> search(@ModelAttribute @Valid SearchRequest searchRequest) {

        rankingService.incrementSearchWordScore(searchRequest.getQuery());

        var result = searchBlogService.search(
                SearchBlogRequest.of(searchRequest.getQuery(),
                        searchRequest.getPage(),
                        searchRequest.getSize(),
                        searchRequest.getSort()));

        return ResponseEntity.ok()
                .body(ApiResponseModel.success(SearchResponse.from(result)));
    }
}
