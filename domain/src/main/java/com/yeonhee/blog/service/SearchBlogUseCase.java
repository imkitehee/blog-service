package com.yeonhee.blog.service;

import com.yeonhee.blog.client.kakao.dto.KakaoSearchBlogResponse;
import com.yeonhee.blog.service.command.SearchBlogCommand;

public interface SearchBlogUseCase {
    KakaoSearchBlogResponse search(SearchBlogCommand searchBlogCommand);
}
