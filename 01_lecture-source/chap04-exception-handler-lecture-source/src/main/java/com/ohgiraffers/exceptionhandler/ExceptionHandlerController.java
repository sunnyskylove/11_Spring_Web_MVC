package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")         // 링크로 가져올거니깐 Getmapping 해준다!
    public String nullPointerExceptionTest(){

        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }
    // >> 첫번째 버튼 클릭하면 오류 메세지 쭉~~나온다.

    @ExceptionHandler(NullPointerException.class)   // Exception 이라는 최상위의로부터 상속 다 받음~??
    public String nullPointerExceptionHandler(NullPointerException exception){

        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";     //template>error> nullpointer라는 html파일만들기!

    }



}
