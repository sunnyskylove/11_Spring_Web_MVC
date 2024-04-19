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

        /* 참고. ReloadableResourceBundleMessageSource 는 spring 에서 메세지 관리하고 다국어 처리를 위해 사용되는 클래스이다.
        *       리로드 가능한 리소스 번들을 통해 메세지를 로드하고 관리하는 역할을 한다. ▽
        * */
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");   // 메세지 리소스번들의 베이스 이름을 설정하는 부분이다. 이 설정으로 Spring 에게 메세지 소스를 찾을 위치를 알려주기
        source.setDefaultEncoding("UTF-8");                 // 메세지를 통해 기본 인코디 설정할 수 있는데 UTF-8로 기본 인코딩 설정함

        /* 참고. 제공하지 않는 언어로 요청 시 MessageSource 에서 사용할 default 언어 -한국 */
        Locale.setDefault(Locale.KOREA);        // 한국어로 기본 로케일(지역)로 설정함
        return source;

    }

}
