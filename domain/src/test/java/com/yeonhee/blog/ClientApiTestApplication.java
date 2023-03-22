package com.yeonhee.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ClientApiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApiTestApplication.class, args);
    }
}
