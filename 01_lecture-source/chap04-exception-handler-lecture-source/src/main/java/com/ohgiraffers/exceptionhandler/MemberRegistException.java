package com.ohgiraffers.exceptionhandler;

public class MemberRegistException extends Exception{
    public MemberRegistException(String msg) {
        super(msg);     // super(부모)에게 msg 전달하기! / this는 자기자신
    }



}
