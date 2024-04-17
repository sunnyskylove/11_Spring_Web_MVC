package com.ohgiraffers.intercepter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/*")
public class InterceptorController {

    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {
        System.out.println("핸들러 메소드 호출함...");
         // 여기까지는 만들어 놨지만 적용시킨 건 안만듦 >> WebConfiguration 만들어서 동작시키는 거 만들기!!

        // * 3초동안 지연되게 만들기~~!!
        // 3초동안 자고 있어서 delay줌~!, 강제처리 throw 하기!
        Thread.sleep(3000);

        return "result";    // 문자열로 결과페이지를 리턴할 것임!

    }

}
