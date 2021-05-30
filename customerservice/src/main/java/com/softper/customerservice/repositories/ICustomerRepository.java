package com.softper.customerservice.repositories;

import com.softper.customerservice.models.Customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    
    @Query("select c from Customer c where c.personId = (:pid)")
    Customer findCustomerByPersonId(@Param("pid")int personId);

}
