package com.Desktop.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Desktop.Application.Entity.categorymaster;

@Repository
public interface CategoryRepository extends JpaRepository<categorymaster, Integer> {
}
