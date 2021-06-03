package com.softper.cargoservice.repositories;

import com.softper.cargoservice.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository extends JpaRepository<Route, Integer> {
}
