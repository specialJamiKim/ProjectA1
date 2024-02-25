package com.projectA1.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name = "f_owner")
public class Owner extends Person {

    @ManyToOne
    @JoinColumn(name = "center_id")
    private FitnessCenter fitnessCenter; // 센터 id

    private String centerName; // 센터 이름
    private String centerAddress; // 센터주소
    private String businessRegistrationNumber; // 사업자 등록번호
}