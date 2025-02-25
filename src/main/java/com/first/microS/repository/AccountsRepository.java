package com.first.microS.repository;

import com.first.microS.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    public Optional<Accounts> findByCustomerId(Long CustomerId);
    public Optional<Accounts> findByCustomerId(long customerId);
}
