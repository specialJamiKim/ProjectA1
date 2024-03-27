package com.projectA1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectA1.model.User;
import com.projectA1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/restful")
public class RestfulController {
   private final UserService userService;
   
   @GetMapping("user")
   public ResponseEntity<List<User>> my1(@RequestParam String cate, @RequestParam int range) {
      // service 처리 해야 함
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
   }
   
   @PostMapping("/user/{bno}")
   public ResponseEntity<Long> my2(@RequestBody User user) {
      // service MemberService에서 등록 처리가 되었을 경우 회원번호가 100번 일 때
      return new ResponseEntity<>(100L, HttpStatus.OK);
   }
   
   @PutMapping("/user/{mno}") //Put=>Update
   public ResponseEntity<Long> my3(@RequestBody User user, @PathVariable Long mno) {
      // service 수정 처리해야 함
      return new ResponseEntity<>(100L, HttpStatus.OK);
   }
   
   @DeleteMapping("/user")
   public ResponseEntity<String> my4(@RequestParam Long mno){
      // service 삭제 처리해야 함
      return new ResponseEntity<>("", HttpStatus.OK);
   }
}