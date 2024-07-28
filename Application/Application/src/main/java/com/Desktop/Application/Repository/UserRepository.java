package com.Desktop.Application.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Desktop.Application.Entity.usermaster;

public interface UserRepository extends JpaRepository<usermaster,Integer> {
	
    Optional<usermaster> findByUsername(String username);
    
}
