package com.softper.userservice.repositories;

import com.softper.userservice.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBalanceRepository extends JpaRepository<Balance, Integer> {

}
