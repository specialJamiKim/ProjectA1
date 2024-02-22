package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//select * from tbl_user4 where username='11'
	  User findByUsername(String username);

	 //삭제 자동제작
	void delete(String username);
}
