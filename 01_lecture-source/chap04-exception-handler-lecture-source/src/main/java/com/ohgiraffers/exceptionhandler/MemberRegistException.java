package com.ohgiraffers.exceptionhandler;

// 사용자 정의 예외처리할땐 exception 상속을 받아야한다!!!!
public class MemberRegistException extends Exception{
    public MemberRegistException(String msg) {
        super(msg);   // super(부모)에게 msg 전달하기! / this 는 자기자신
    }

}
