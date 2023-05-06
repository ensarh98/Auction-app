package com.auctionapp.db.repository;

import com.auctionapp.db.model.CategoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryRecord, Integer> {
}
