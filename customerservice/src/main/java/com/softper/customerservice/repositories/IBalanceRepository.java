package com.softper.customerservice.repositories;

import com.softper.customerservice.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBalanceRepository extends JpaRepository<Balance, Integer> {

}
