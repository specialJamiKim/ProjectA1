package com.projectA1.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "f_owner")
public class Owner extends Person {

    @ManyToOne
    @JoinColumn(name = "center_id")
    private FitnessCenter fitnessCenter;
    //private List<FitnessCenter> fitnessCenter; // 센터 id

    private String businessRegistrationNumber; // 사업자 등록번호
}