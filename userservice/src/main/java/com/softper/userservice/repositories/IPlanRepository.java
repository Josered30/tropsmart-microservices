package com.softper.userservice.repositories;

import com.softper.userservice.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, Integer> {

    
    @Query("select p from Plan p where p.totalPrice = (:price)")
    List<Plan> findPlansByPriceValue(@Param("price") double priceValue);

    @Query("select p from Plan p where p.totalPrice > (:price)")
    List<Plan> findPlansHigherThanPriceValue(@Param("price") double priceValue);

    @Query("select p from Plan p where p.totalPrice < (:price)")
    List<Plan> findPlansLessThanPriceValue(@Param("price") double priceValue);
    
}

