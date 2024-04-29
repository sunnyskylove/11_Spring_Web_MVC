package com.ohgiraffers.chap08jakkiesfetchlecturesource;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class MemberDTO {

    private int no;
    private String name;
    private int age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-HH:mm:ss", timezone = "Asia/Seoul")
    private Date enrollDate;

    public MemberDTO() {}

    public MemberDTO(int no, String name, int age, Date enrollDate) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.enrollDate = enrollDate;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", enrollDate=" + enrollDate +
                '}';
    }

}
