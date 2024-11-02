package com.vijay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	

}
