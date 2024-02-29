package com.projectA1.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


//Reservation.java
@Entity(name = "f_reservation")
@Getter
@Setter
public class Reservation {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @OneToOne
 @JoinColumn(name = "center_id") // "visit_centerNumber"를 "center_id"로 변경
 private FitnessCenter center;

 @OneToOne
 @JoinColumn(name = "user_id") // "user_id"를 실제 외래 키 열과 일치하도록 변경
 private User user;

 @Temporal(TemporalType.TIMESTAMP)
 private Date reservationTime;

}

