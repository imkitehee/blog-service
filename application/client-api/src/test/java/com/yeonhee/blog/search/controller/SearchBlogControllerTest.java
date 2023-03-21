package com.yeonhee.blog.search.controller;

import com.yeonhee.blog.fixture._블로그_검색;
import com.yeonhee.blog.ranking.service.RankingService;
import com.yeonhee.blog.search.dto.SearchRequest;
import com.yeonhee.blog.search.service.SearchBlogService;
import com.yeonhee.blog.search.service.dto.SearchBlogRequest;
import com.yeonhee.blog.search.service.dto.SearchBlogResponse;
import com.yeonhee.blog.util.RestDocsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(SearchBlogController.class)
@DisplayName("블로그 검색 Controller")
class SearchBlogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RankingService rankingService;

    @MockBean
    private SearchBlogService searchBlogService;


    @Test
    @DisplayName("검색어에 해당하는 블로그 목록을 리턴한다.")
    void search() throws Exception {
        // given
        SearchBlogResponse searchBlogResponse = _블로그_검색.getSearchBlogResponse();
        given(searchBlogService.search(any(SearchBlogRequest.class))).willReturn(searchBlogResponse);

        // when
        SearchRequest request = _블로그_검색.getSearchRequest("검색어");

        final ResultActions resultActions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/search/blog")
                                .param("query", request.getQuery())
                                .param("page", request.getPage().toString())
                                .param("size", request.getSize().toString())
                                .param("sort", request.getSort().name())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk());

        // then
        resultActions.andDo(
                document("v1/search/blog",
                        RestDocsUtils.getDocumentRequest(),
                        RestDocsUtils.getDocumentResponse(),
                        requestParameters(
                                parameterWithName("query").description("검색어"),
                                parameterWithName("page").description("결과 페이지 번호").optional(),
                                parameterWithName("size").description("한 페이지에 보여질 문서 수").optional(),
                                parameterWithName("sort").description("결과 문서 정렬 방식(ACCURACY: 정확도순, RECENCY: 최신순)").optional()
                        ),
                        responseFields(beneathPath("data"),
                                fieldWithPath("page").type(JsonFieldType.OBJECT).description("pagination 정보"),
                                fieldWithPath("page.totalCount").type(JsonFieldType.NUMBER).description("검색된 총 문서 수"),
                                fieldWithPath("page.pageableCount").type(JsonFieldType.NUMBER).description("total_count 중 노출 가능 문서 수"),
                                fieldWithPath("page.isEnd").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("results").type(JsonFieldType.ARRAY).description("결과 목록"),
                                fieldWithPath("results.[].title").type(JsonFieldType.STRING).description("블로그 제목"),
                                fieldWithPath("results.[].contents").type(JsonFieldType.STRING).description("블로그 내용"),
                                fieldWithPath("results.[].url").type(JsonFieldType.STRING).description("블로그 글 URL"),
                                fieldWithPath("results.[].blogName").type(JsonFieldType.STRING).description("블로그 이름"),
                                fieldWithPath("results.[].postDate").type(JsonFieldType.STRING).description("블로그 작성일(YYYY-MM-DD)")
                        )
                )
        );
    }
}
