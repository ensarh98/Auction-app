package com.auctionapp.db.repository;

import com.auctionapp.db.model.SubcategoryRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryRecord, Integer> {

}
