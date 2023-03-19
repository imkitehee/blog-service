package com.yeonhee.blog.search.controller;


import com.yeonhee.blog.search.dto.SearchRequest;
import com.yeonhee.blog.response.ApiResponseModel;
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

    @GetMapping("v1/search/blog")
    public ResponseEntity<ApiResponseModel> search(@ModelAttribute @Valid SearchRequest searchRequest) {

        var result = searchBlogService.search(
                SearchBlogRequest.of(searchRequest.getQuery(),
                        searchRequest.getPage(),
                        searchRequest.getSize(),
                        searchRequest.getSort()));

        return ResponseEntity.ok()
                .body(ApiResponseModel.success(result));
    }
}
