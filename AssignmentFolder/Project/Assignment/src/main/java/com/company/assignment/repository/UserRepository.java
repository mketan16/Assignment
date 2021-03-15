package com.company.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.assignment.models.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity, Integer>{
	
	@Query(value="select * from user where BINARY userName=:userName and BINARY password=:password", nativeQuery=true)
	Object authontication(@Param("userName") String userName,@Param("password") String password);
}

