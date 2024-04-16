package com.ohgiraffers.request_mapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller             // 웹에서 읽힐 수 있도록 Bean으로 등록!!
public class MethodMappingTestController {

    /* 1. 메소드 방식 미지정 */
    @RequestMapping("/menu/regist")
    public String registMenu(Model model /*HttpServletRequest request*/) {
        // (HttpServletRequest >>원랜 가로안에있었음!, Model로 바꿈!_스프링에서 제공하는걸로 임포트 하기!!)를 직접사용하지 않고, 서블릿은 내부에 숨겨났으니 model and view 쓰기(PSA),상속개념으로 쓰기!!>> Spring이 요구하고 있는 개념(IOC컨테이너)

        model.addAttribute("message",
                "신규 메뉴 등록용 핸들러 메소드 호출됨...");

//        request.setAttribute("message","신규 메뉴 등록용 핸들러 메소드 호출됨");
// 동작하는 건 Model과 같다. 다만, 의존성등의 문제로 스프링측에서 제공하고 있는 위의 Model을 사용한다!!

        /* 필기.
         *   Thymeleaf 의존성을 추가하게 되면,
         *   ThymeleafViewResolver 라는 녀석이 생기게 된다.(대신 수행해줌)
         *   - 접두사(Prefix) /resource/templates
         *   - 접미사 .html
         *   (이렇게 앞,뒤로 붙여준다~~)
         * */

        return "mappingResult";

    }

    /* 2. 메소드 방식 지정 */
    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modifyMenu(Model model) {

        model.addAttribute("message"
                , "GET 방식의 메뉴 수정용 핸들러 메소드 호출함...");

        return "mappingResult";            // Get은 되고, Post는 안된다~~!!

    }

    /* 3. 요청 메소드 전용 어노테이션(스프링 4.3 버전부터 지원) */
    /* 필기.  (GET과 Post가 주로 쓰인다!!)
     *   요청 메소드        어노테이션
     *   POST             @PostMapping
     *   GET              @GetMapping
     *   PUT              @PutMapping
     *   DELETE           @DeleteMapping
     *   PATCH            @PatchMapping
     * */

    @GetMapping("/menu/delete")
    public String deleteMenu(Model model) {

        model.addAttribute("message",
                "GET 방식의 삭제용 핸들러 메소드 호출함...");

        return "mappingResult";

    }

    @PostMapping("/menu/delete")
    public String deleteAction(Model model) {

        model.addAttribute("message",
                "POST 방식의 삭제용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

}
