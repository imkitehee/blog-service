package com.yeonhee.blog.util;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

public class RestDocsUtils {

    /**
     * 요청 선행 처리 설정.
     */
    public static OperationRequestPreprocessor getDocumentRequest() {
        return Preprocessors.preprocessRequest(
                Preprocessors.modifyUris()
                        .scheme("https")
                        .host("docs.api.com")
                        .removePort(),
                Preprocessors.prettyPrint()
        );
    }

    /**
     * 응답 선행 처리 설정.
     */
    public static OperationResponsePreprocessor getDocumentResponse() {
        return Preprocessors.preprocessResponse(Preprocessors.prettyPrint());
    }
}
