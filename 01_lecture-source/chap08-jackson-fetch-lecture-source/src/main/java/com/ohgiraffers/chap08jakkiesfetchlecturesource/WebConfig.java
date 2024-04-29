package com.ohgiraffers.chap08jakkiesfetchlecturesource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig {

    @Bean
    MappingJackson2JsonView jsonView() {

    }
}
