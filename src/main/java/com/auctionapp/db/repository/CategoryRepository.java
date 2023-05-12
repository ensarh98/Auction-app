package com.auctionapp.db.repository;

import com.auctionapp.db.model.CategoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Category instances.
 *
 * @author Ensar Horozović
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryRecord, Integer> {
}
