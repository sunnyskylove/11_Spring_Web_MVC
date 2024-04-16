package com.ohgiraffers.request_mapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

@Controller
@RequestMapping("/order/*")
/* 필기.
 *   클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다.
 *   클래스 레벨에 공통 URL 을 설정하면, 핸들러 메소드에 URL 에 중복되는
 *   내용을 작성하지 않아도 된다.
 *   이 때 와일드카드(*: 모든것) 를 이용해서 포괄적인 URL 패턴을 이용할 수 있다.
 * */
public class ClassMappingTestController {

    @GetMapping("/regist")          // regist 앞에 regist가 있다! (위에 order/* 으로 지정했으므로 밑에는 뒤에것만 작성함)
    public String registOrder(Model model) {

        model.addAttribute("message"
                , "GET 방식의 주문 등록용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    /* 참고. 여러개의 패턴 매핑 */
//    @PostMapping({"/modify","/delete"})       // 이렇게 해도 가능!
    @RequestMapping(value = {"/modify", "/delete"}, method = RequestMethod.POST)
    // 중가로를 사용해서 한페이지에 나옴 => 코드를 한군데 모아서 사용해서 응집도 높임.
    public String modifyAndDelete(Model model) {

        model.addAttribute("message"
                , "POST 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용" +
                        "핸들러 메소드 호출됨...");

        return "mappingResult";

    }

    /*  필기.
     *    @PathVariable 어노테이션을 이용해 요청 path 로 부터 변수를 받아올 수 있다.
     *    path variable 로 전달되는 { 변수명 } 은 반드시 매개변수명과 동일해야 한다.
     *    만약, 동일하지 않으면 @PathVariable("이름")을 설정해주어야 한다!!
     * */
    @GetMapping("/detail/{orderNo}")        // 실제로는 3으로 넘겼는데(index.html안) orderNo로 저장??
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo2) {

        model.addAttribute("message"
                , orderNo2 + "번 주문 상세 내용 조회용 핸들러 메소드 호출됨...");

        return "mappingResult";

    }

}
