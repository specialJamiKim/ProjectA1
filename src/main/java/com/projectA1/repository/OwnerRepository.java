package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{
	//이 부분 PrincipalDetail.java 때문에 필요
//	Owner findByOwnername(String ownerName);

}
