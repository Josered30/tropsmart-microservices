package com.softper.driverservice.repositories;

import com.softper.driverservice.models.Soat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoatRepository extends JpaRepository<Soat, Integer> {
}
