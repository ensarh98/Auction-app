package com.auctionapp.db.repository;

import com.auctionapp.db.model.UserRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRecord, Integer> {

	UserRecord findOneByEmail(String email);

	@Query("""
            select count(u.id) from UserRecord as u where u.email = :email
            """)
	public Integer isExistsUserByEmail(String email);
}
