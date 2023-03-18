package com.yeonhee.blog.controller;


import com.yeonhee.blog.dto.SearchRequest;
import com.yeonhee.blog.dto.SearchResponse;
import com.yeonhee.blog.response.ApiResponseModel;
import com.yeonhee.blog.service.SearchBlogUseCase;
import com.yeonhee.blog.service.command.SearchBlogCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SearchBlogController {

    private final SearchBlogUseCase searchBlogUseCase;

    @GetMapping("v1/search/blog")
    public ResponseEntity<ApiResponseModel> search(@ModelAttribute @Valid SearchRequest searchRequest) {

        var result = searchBlogUseCase.search(
                SearchBlogCommand.of(searchRequest.getQuery(),
                        searchRequest.getPage(),
                        searchRequest.getSize(),
                        searchRequest.getSort()));

        return ResponseEntity.ok()
                .body(ApiResponseModel.success(SearchResponse.fromKakao(result)));
    }
}
