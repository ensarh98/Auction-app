package com.auctionapp.db.repository;

import com.auctionapp.db.model.BidRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BidRepository extends JpaRepository<BidRecord, Integer> {

    @Query("""
            select b.bidPrice from BidRecord as b where b.id = :id
        """)
    Double getBidPrice(Integer id);

    @Query("""
            select b.bids from BidRecord as b where b.id = :id
        """)
    Integer getBidBids(Integer id);

    @Query("""
            select count(*) from BidRecord as b where b.itemId = :itemId
        """)
    Integer countBidByItemId(Integer itemId);
}
