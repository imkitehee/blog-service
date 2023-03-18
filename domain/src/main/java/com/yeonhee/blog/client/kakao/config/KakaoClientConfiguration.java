package com.yeonhee.blog.client.kakao.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoClientConfiguration {

    @Value("${feign.kakao-api.rest-api-key}")
    private String restAPIKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "KakaoAK " + restAPIKey);
    }
}
