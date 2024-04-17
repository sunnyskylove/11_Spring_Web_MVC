package com.ohgiraffers.intercepter;

// 컨트롤러의 영향없이 동작하게 만들기~~

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component          // Component 빈으로 등록!
public class StopWatchIntercepter implements HandlerInterceptor {

    private final MenuService menuService;
    // Menuservice 라는 생성자를 가지고 옴 (사용할 준비!!)

    @Autowired
    public StopWatchIntercepter(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 참고. 전처리 메소드 : 컨트롤러가 동작하기 전에 수행한다.
            pre 라고 하는건 전에 동작하는 메소드(동작하기 전에 움직이는 메소드) */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandler 호출함...");            // 전에~~호출???

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        /* 참고. true 이면 컨트롤러를 이어서 호출하게 한다. false 면 핸들러 메소드를 호출하지 않는다.(얜 전처리 하고, controller로 안간다.)
        *       결론적으로 보낼거냐 안보낼거냐~~ 다르다. */
        return true;
    }

    /* 참고. 후처리 메소드 : 컨트롤러가 동작후 (컨트롤러 >> 후처리하는 앞뒤로 감싼 것), Model and view가 들어있어서 view를 반환해줘야 한다. */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출함...");        // 후에~~호출??

         long startTime = (long) request.getAttribute("startTime");
         long endTime = System.currentTimeMillis();

         modelAndView.addObject("interval", endTime - startTime);

    }

    /* 참고. 마지막에 호출하는 메소드 / 모든작업이 완료되고 나서 가장 마지막에 되는 것 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterComplate 호출함...");

        menuService.method();
    }
}