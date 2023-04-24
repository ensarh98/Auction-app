package com.example.springbootreactjsauctionapp.repositories;

import com.example.springbootreactjsauctionapp.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
