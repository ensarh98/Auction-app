package com.example.springbootreactjsauctionapp.repositories;

import com.example.springbootreactjsauctionapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
