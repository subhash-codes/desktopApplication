package com.Desktop.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Desktop.Application.Entity.productmaster;

public interface ProductRepository extends JpaRepository<productmaster, Integer> {
	

}
