package com.yeonhee.blog.search.client.naver.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverClientConfiguration {

    @Value("${feign.naver-api.client-id}")
    private String clientId;

    @Value("${feign.naver-api.client-secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor naverRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret);
    }
}
