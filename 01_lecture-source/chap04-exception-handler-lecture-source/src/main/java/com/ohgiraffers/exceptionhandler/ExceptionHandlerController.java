package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;


//참고. ㅁ 해당 클래스 안 타입에 맞는 예외처리 잡는곳!!(scope 지역변수/ 이 지역에서 잡히는데~~)
@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")    // 링크로 가져올거니깐 Getmapping 해준다!(작동하게 만들기 위한)
    public String nullPointerExceptionTest(){

        String str = null;
        System.out.println(str.charAt(0));
        // 리턴 의미없음. 그 전에 오류때문에(오류가 안나야 리턴된다) main 페이지로 안가니깐~

        return "/";         // ▶ main 페이지로 리턴!
    }
    // >> (예외처리 만들기 전) 첫번째 버튼 클릭하면, 페이지에서 오류 메세지 쭉~~나온다.

    // 따라서, ↓ nullpoint 오류 발생시 예외처리 만들어주기!!!
    @ExceptionHandler(NullPointerException.class)
    // 바로 위의 nullpointer 가 오류나면, 여기 handler에서 잡겠음!

    public String nullPointerExceptionHandler(NullPointerException exception){
        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";
        // template> error> nullpointer라는 html파일만들기! >> 이동!
    }

    // 1번째 버튼2
    @GetMapping("controller-user")  // 오류 예외 만들기!
    public String userExceptionHandler() throws MemberRegistException {
        boolean check = true;
        if (check) {
            throw new MemberRegistException("회원이 아닙니다");
            //MemberRegistException에서 만든 예외처리로 일처리함~
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionTest(MemberRegistException exception, Model model){
        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception",exception);
                // msg 가 value가 되게 만들어준것

        return "error/memberRegist";

    }




}
