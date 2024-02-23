package com.projectA1.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
@Entity(name="f_user")
public class User {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId; //회원번호
	
	@ManyToOne
	@JoinColumn(name = "user_centerNumber") //헬스장 번호
	private FitnessCenter centerNumber;
	
	private String username; //회원이름
	private String loginId; //로그인 아이디
	private String password; //로그인 비밀번호
	private Date birthDay; //생년월일
	private String phoneNumber; // 전화번호
	private String addr;// 주소
	private String email; //이메일
	private Date joinDate; // 가입일자
	private String role; //권한? 필요한지 모르겠음 => 해결해야함
}