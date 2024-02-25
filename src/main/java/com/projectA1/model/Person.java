package com.projectA1.model;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 번호(user,owner)

    private String name; // 이름(user,owner)
    private String email; // 이메일 => 로그인 아이디
    private String password; // 로그인 비밀번호
    private String phoneNumber; // 전화번호
    private String gender; //성별
    private String address; // 주소
    private Date joinDate; // 가입일자
}
