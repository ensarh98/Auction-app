package com.example.springbootreactjsauctionapp.repositories;

import com.example.springbootreactjsauctionapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String login, String password);

    Optional<User> findFirstByEmail(String email);
}
