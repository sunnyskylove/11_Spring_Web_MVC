package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// index 대신 밑의 html파일들을 불러오려고 서버열어주려고!! / 동적으로 만들려고 mainController 만듦
@Controller
public class MainController {

    @RequestMapping (value = {"/", "main"})
    //  url '/'로 main 페이지가 동작하도록 만들어 놓기~!

    public String main() {

        return "main";      // 밑의 메인 페이지만 열수 있따.
    }
}