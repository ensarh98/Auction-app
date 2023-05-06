package com.auctionapp.db.repository;

import com.auctionapp.db.model.SubcategoryRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryRecord, Integer> {

    @Query("""
            select count(*) from SubcategoryRecord s where s.categoryId = :categoryId
            """)
    Integer countByCategoryId(Integer categoryId);
}
