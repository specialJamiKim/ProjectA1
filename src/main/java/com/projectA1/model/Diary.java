package com.projectA1.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Diary {

    
	@ManyToOne
    @JoinColumn(name = "diary_userId", nullable = false)
    private User userid;

    private Date date; //날짜
    private String title; //제목

    @Column(length = 1000)
    private String content; // 내용
}