package com.softper.configservice.repositories;

import com.softper.configservice.models.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfigurationRepository extends JpaRepository<Configuration, Integer> {


}
