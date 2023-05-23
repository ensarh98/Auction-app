package com.auctionapp.db.repository;

import com.auctionapp.db.model.ItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Item instances.
 *
 * @author Ensar Horozović
 */
@Repository
public interface ItemRepository extends JpaRepository<ItemRecord, Integer> {


}
