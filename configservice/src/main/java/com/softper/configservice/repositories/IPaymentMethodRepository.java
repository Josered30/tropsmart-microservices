package com.softper.configservice.repositories;

import com.softper.configservice.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    //@Query("select p from PaymentMethod p where p.configuration.user.id = (:uid)")
    //List<PaymentMethod> findPaymentMethodByUserId(@Param("uid") Integer userId);

}
