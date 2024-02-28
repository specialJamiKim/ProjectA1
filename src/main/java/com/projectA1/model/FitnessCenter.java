// FitnessCenter.java
package com.projectA1.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "f_center")
@Getter @Setter
public class FitnessCenter {

	//센터id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키
    
    private String name; // 센터 이름
    private String address; // 주소
    private String phoneNumber; // 전화번호
    private Long dailyPassPrice; // 일일권 가격

    // 수정된 부분: 오픈 시간과 마감 시간의 타입을 TemporalType.TIMESTAMP로 변경
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime; // 오픈시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date closingTime; // 마감시간
    
    // 수정된 부분: Owner와의 관계 설정
    @OneToMany(mappedBy = "fitnessCenter", cascade = CascadeType.ALL)
    private List<Owner> owners; // 이 FitnessCenter를 소유한 Owner 목록
}
