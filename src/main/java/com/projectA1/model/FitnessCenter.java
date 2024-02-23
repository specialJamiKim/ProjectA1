package com.projectA1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name="f_center")
@Getter @Setter
public class FitnessCenter {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long centerNumber; // 피트니스 센터 번호 (기본키)
    
    @ManyToOne
    @JoinColumn(name = "businessRegistrationNumber")
    private Owner owner; // 사업자 등록 번호
    
    private String branchName; // 상호명
    private String address; // 주소
    private String phoneNumber; // 전화번호
    private int dailyPassPrice; //일일권 가격
    private String openTime; //오픈시간
    private String closingTime; //마감시간
    
}