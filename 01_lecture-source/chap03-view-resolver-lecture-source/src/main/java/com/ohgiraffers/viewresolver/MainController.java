package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/main"})  // /아무런 없을때 / ,서버에 들렸다 옴!
    public String main(){


        return "main";          // main이라는 걸 prefix해서 리턴해줌~~


    }

}
