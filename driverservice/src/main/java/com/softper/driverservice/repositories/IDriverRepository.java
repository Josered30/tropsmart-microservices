package com.softper.driverservice.repositories;


import com.softper.driverservice.models.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDriverRepository extends JpaRepository<Driver, Integer> {

    @Query("select d from Driver d where d.personId = (:pid)")
    Optional<Driver> findDriverByPersonId(@Param("pid")int personId);
}
