package com.example.springbootreactjsauctionapp.repositories;

import com.example.springbootreactjsauctionapp.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
