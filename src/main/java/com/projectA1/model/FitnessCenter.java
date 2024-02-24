// FitnessCenter.java
package com.projectA1.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "f_center")
@Getter @Setter
public class FitnessCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @ManyToMany
    @JoinTable(
        name = "user_visit_fitness_center",
        joinColumns = @JoinColumn(name = "fitness_center_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    private String branchName; // 상호명
    private String address; // 주소
    private String phoneNumber; // 전화번호
    private int dailyPassPrice; // 일일권 가격

    // 수정된 부분: 오픈 시간과 마감 시간의 타입을 TemporalType.TIMESTAMP로 변경
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime; // 오픈시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date closingTime; // 마감시간
}
