package com.ohgiraffers.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
public class ContextConfiguration {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
    // (한국어)다국적 설정에 대해 읽을 수 있게 @bean 도 만들어주고, Reload~만들어준다!! 해당 내용은 아래에~~

        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* 참고. 제공하지 않는 언어로 요청 시 MessageSource에서 사용할 default 언어 한국 */
        Locale.setDefault(Locale.KOREA);
        return source;

    }

}
