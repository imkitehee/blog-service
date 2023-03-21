package com.yeonhee.blog.ranking.controller;

import com.yeonhee.blog.fixture._랭킹_조회;
import com.yeonhee.blog.ranking.service.RankingService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@WebMvcTest(FindSearchWordRankingController.class)
@DisplayName("인기 검색어 순위 조회 Controller")
class FindSearchWordRankingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RankingService rankingService;

    @Test
    @DisplayName("findSearchWordRanking() 메서드는 인기 검색어 최대 10를 조회한다.")
    void findSearchWordRanking() throws Exception {

        // given
        given(rankingService.getSearchWordRanking()).willReturn(_랭킹_조회.getRankingList());
        // when
        final ResultActions resultActions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/ranking/search-word")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk());

        // then
        resultActions.andDo(
                document("v1/ranking/search-word",
                        RestDocsUtils.getDocumentResponse(),
                        responseFields(beneathPath("data"),
                                fieldWithPath("query").type(JsonFieldType.STRING).description("검색어"),
                                fieldWithPath("score").type(JsonFieldType.NUMBER).description("검색횟수")
                        )
                )
        );
    }
}
