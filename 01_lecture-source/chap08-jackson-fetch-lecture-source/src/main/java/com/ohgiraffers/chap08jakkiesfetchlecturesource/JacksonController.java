package com.ohgiraffers.chap08jakkiesfetchlecturesource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JacksonController {

    /* 필기.
    *   *비동기 통신
    *   페이지 전체를 새로고침 하지 않고도 수행되는 통신 방법
    *   서버로부터 데이터를 받아 전체 페이지가 아닌
    *   일부분만 업데이트를 할 수 있게 만들어준다.
    * */

    /* 필기.
    *   -장점
    *   : 웹 서버의 응답을 기다리지 않고, 데이터를 빠르게 처리할 수 있다.
    *     서버에서 데이터를 전송하면 되므로 응답에 대한 코드 작성 양이 줄어든다.
    *     페이지의 리로딩 없이 처리할 수 있다.
    *   -단점
    *   : 한 페이지에서 연속으로 사용하게 되면, 서버의 부하를 가중시킬 수 있고,
    *     리소스가 누적되어 페이지 내의 속도가 저하될 수 있다.
    * */

    /* 필기.
    *   *jackson 라이브러리
    *   java 객체를 json 형식으로 변환하거나
    *   json 형식을 java 객체로 변환하는 것에 사용된다.
    * */

    private final List<MemberDTO> memberList;

    public JacksonController () {
        memberList = new ArrayList<>();
        memberList.add(new MemberDTO(1,"홍길동",20,new Date(System.currentTimeMillis())));
        memberList.add(new MemberDTO(2,"유관순",16,new Date(System.currentTimeMillis())));
        memberList.add(new MemberDTO(3,"이순신",40,new Date(System.currentTimeMillis())));
        memberList.add(new MemberDTO(4,"윤봉길",33,new Date(System.currentTimeMillis())));
    }

    @GetMapping(value = "jackson1", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getMemberResponseBody() throws JsonProcessingException {

        /* 참고. jackson 라이브러리에서 제공하고 있는 클래스. java - json 변환에 사용된다. */
        ObjectMapper mapper = new ObjectMapper();       // ObjectMapper : 객체를 뭔가로 감쌀 것임! 아니면 매칭을 시킬 것임!!

        return mapper.writeValueAsString(memberList);
    }

    /* 참고. jsonView 를 이용한 응답 (좀 더 시각적으로 확인할 수 있다.) */
    @GetMapping("jackson2")
    public ModelAndView getMemberModelAndView(ModelAndView mv, HttpServletRequest response) throws JsonProcessingException {

        response.setContentType("application/json; charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();

        mv.addObject("memberList", mapper.writeValueAsString(memberList));
        mv.setViewName("jsonView");

        return mv;
    }

}
