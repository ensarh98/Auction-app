package com.example.springbootreactjsauctionapp.repositories;

import com.example.springbootreactjsauctionapp.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
