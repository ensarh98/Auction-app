package com.example.springbootreactjsauctionapp.repositories;

import com.example.springbootreactjsauctionapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
