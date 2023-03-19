package com.yeonhee.blog.search.dto;

import com.yeonhee.blog.type.SortType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SearchRequest {
    @NotBlank
    private String query;
    private Integer page = 1;
    private Integer size = 10;
    private SortType sort = SortType.ACCURACY;
}
