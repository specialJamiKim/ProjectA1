package com.projectA1.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "f_owner")
@Getter @Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 오너 아이디(식별번호)

    @ManyToOne
    @JoinColumn(name = "fitnessCenter_id")
    private FitnessCenter fitnessCenter; // 센터 id

    private String businessRegistrationNumber; // 사업자 등록번호
    private String ownerName; // 사업주
    private String centerName; // 센터 이름
    private String centerAddr; // 센터주소
    private String ownerPhoneNumber; // 전화번호
}