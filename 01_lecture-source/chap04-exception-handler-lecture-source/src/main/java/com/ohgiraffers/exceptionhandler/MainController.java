package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping (value = {"/", "main"}) //  '/'로 main 페이지가 동작하도록 만들어 놓기~!
    public String main() {

        return "main";
    }
}