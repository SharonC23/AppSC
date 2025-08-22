package com.miApp.AppS.repository;

import com.miApp.AppS.entity.Category;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

   Category findByName(String name);

}
