package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/first/*")
@SessionAttributes("id")      // 키 값이 여러개일떄 names 라고 한다! (예: names={"id","pwd"} )
public class FirstController {

    /* 필기.
     *   컨트롤러의 핸들러 메소드의 반환 값을 void 형으로 설정하게 되면
     *   요청 주소가 view 의 이름이 된다.
     *   => /first/regist 요청이 들어오면 /first/regist 뷰를 응답한다.
     * */

    @GetMapping("/regist")
    public void regist() {}        // view로 보임

    /* 필기.
     *   WebRequest 로 요청 파라미터 전달 받기
     *   WebRequest 라는 녀석은 HttpServletRequest 의 정보를 대부분 가지고 있는
     *   API 로 Servlet 에 종속(휘둘리지?)적이지 않다.
     *   Spring 의 일부이기 때문에 Servlet 을 사용하는 것처럼 동일하게 사용할 수 있다.
     * */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        // 꺼내오기!!
        String name = request.getParameter("name");                     // regist파일에 있는 해당의 name에 있는 값들이 key값이다!
        int price = Integer.parseInt(request.getParameter("price"));   //넘어오는 값 parcing해줘서 오류 풀기~
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에" +
                price + "원으로 등록했습니다.!!";

        model.addAttribute("message", message);

        return "first/messagePrinter";

    }

    @GetMapping("/modify")
    public void modify() {}

    /* 필기.
     *   아무값도 넘기지 않았을 때
     *    - required 속성 디폴트 값 true
     *   이 속성은 false 로 하게 되면 해당 name 값이 존재하지 않아도 null 로 처리하며
     *   에러가 발생한다.
     *   - defaultValue 를 사용하게 되면 기본 값으로 사용할 수 있다.
     * */

    @PostMapping("modify")
    public String modifyMenuPrice(Model model,
                                  @RequestParam(required = false) String modifyName,
                                  @RequestParam(defaultValue = "0") int modifyPrice) {

        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "원으로 변경하였습니다!!";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @PostMapping("modifyAll")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters) {

        String modifyMenu = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = modifyMenu + "메뉴의 가격을 " + modifyPrice + "원으로 변경하였습니다!!";

        model.addAttribute("message", message);

        return "first/messagePrinter";

    }

    @GetMapping("/search")
    public void search() {}

    /* 필기.
    *   3. @ModelAttribute 를 이용하는 방법
    *   DTO 같은 모델을 커멘드 객체로 전달 받는다.
    *   @ModelAttribute("모델에 담을 key 값"). 근데 이름을 정하지 않았다?
    *   -> 타입의 앞글자를 소문자로 한 네이밍 규칙을 따른다.
    *   해당 어노테이션은 생략이 가능하지만 명시적으로 작성하는 것이 좋다.
    * */


    @PostMapping("search")
    public String searchMenu(@ModelAttribute MenuDTO menu) {
    // @ModelAttribute 는 생략을 해도 알아서 값이 들어가지만, 명시적으로 작성하는 것이 좋다.
    // 그리고 값을 정하고 싶으면, @Model~뒤에다가 ()쓰고 그 안에 네임명 작성해준다!
    // View페이지에 담을 것을 알아서 담아준다. thymeleaf로 작성해서 담아줄 수 있다?!!

        System.out.println("menu = " + menu);

        return "first/searchResult";    //app 실행했을때, 입력값이 들어가있음을 확인할 수 있다(web에선 오류)

    }

    @GetMapping("login")
    public void login() {}

    /* 4-1. session 이용하기
    *   HttpSession 을 매개변수로 선언하면 핸들러 메소드 호출 시 새션 객체를 넣어서 호출한다.
    * */

    @PostMapping("login1")
    public String sessionTest1(HttpSession session, @RequestParam String id) {

        session.setAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout")
    public String logoutTest1(HttpSession session) {

        session.invalidate();      // 강제로 닫는 거 만드는 호출!

        return "first/loginResult";

    }

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {

        model.addAttribute("id", id);

        return "first/loginResult";
    }

    /*  참고. SessionAttributes 로 등록 된 값은 session 의 상태를 관리하는
    *        SessionStatus 의 setComplete() 메소드를 호출해야 사용이 만료된다.
    * */


    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus) {

        /* 현재 컨트롤러 세션에 저장된 모든 정보를 제거한다.
         *   개별적인 제거는 불가능하다.
         *  */
        sessionStatus.setComplete();

        return "first/loginResult";
    }

    @GetMapping("body")
    public void body(){}
    //void 형태로 view 반환 할 것이다!!, first 를 페이지로서 바로 보여줄수 있게 만들 수 있음!!

    @PostMapping("body")
    /* 참고. @RequestBody
    *   http 본문 자체를 읽는 부분을 모델로 변환시켜 주는 어노테이션
    *   출력시 쿼리스트링 형태의 문자열이 전송된다.
    *   JSON 으로 전달하는 경우 Jackson 의 컨버터로 자동 파싱하여 사용할 수 있다.
    *
    *   참고. RestAPI 작성 시 사용되며, 일반적인 form 전송을 할 때는 거의 사용하지 않는다.
    *    @RequestHeader 헤더에 대한 정보 가져오기
    * */
    public void bodyTest(@RequestBody String body, @RequestHeader("content-type") String contentType) throws UnsupportedEncodingException {      // ("content-type")이라고 만들고 이걸 옆에 있는 contentType 변수에다가 저장하기!

        System.out.println("body = " + body);
        System.out.println(contentType);        // 제대로 들어갔는지 확인!!
        System.out.println(URLDecoder.decode(body, "UTF-8"));
        // 바꾸고 싶은 URL 을 가로안에 전달인자들 넣어준다!(body 에 한꺼번에 url 방식으로 쏴준다~)
        // enc 는 파일 업로드할때 encoding 을 적어놓는것, 그리고 빨간줄은 throw 로 예외처리해주기!
        // 나중에 많이 쓰임. 지금은 간단하게 봐보기~~

        // 웹의 결과값은 intellij의 콘솔창에 뜬다.
    }


}


