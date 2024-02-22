package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{

}
