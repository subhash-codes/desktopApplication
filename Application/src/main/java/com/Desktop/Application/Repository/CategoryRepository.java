package com.Desktop.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.Desktop.Application.Entity.categorymaster;

public interface CategoryRepository extends JpaRepository<categorymaster, Integer>{
	

	//public categorymaster findCategoryNameByCategoryName(String categoryname);
	
}
