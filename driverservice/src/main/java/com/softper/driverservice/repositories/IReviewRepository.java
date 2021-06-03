package com.softper.driverservice.repositories;

import com.softper.driverservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
    //@Query("select r from Review r where r.cargo.customer.id = (:customerId)")
    //List<Review> findReviewsByCustomerId(@Param("customerId")int customerId);

    //@Query("select r from Review r where r.cargo.service.servicesRequest.driver.id = (:driverId)")
    //List<Review> findReviewsByDriverId(@Param("driverId")int driverId);
}
