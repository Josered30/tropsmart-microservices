package com.softper.customerservice.services;

import com.softper.customerservice.models.Customer;
import com.softper.customerservice.resources.comunications.CustomerBoundResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerService extends ICrudService<Customer>{
    CustomerBoundResponse findCustomerById(int customerId);
    CustomerBoundResponse findAllCustomers();
    CustomerBoundResponse rechargeCreditsByCustomerId(int customerId, double creditUnits);
    Customer generateNewCustomer(int personId);
    Customer findCustomerByPersonId(int personId);
    Customer getCustomerById(int customerId);
}
