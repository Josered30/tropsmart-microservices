package com.softper.customerservice.servicesImp;

import com.softper.customerservice.resources.comunications.CustomerBoundResponse;
import com.softper.customerservice.resources.comunications.UserBoundResponse;
import com.softper.customerservice.resources.outputs.BalanceOutput;
import com.softper.customerservice.resources.outputs.CustomerOutput;
import com.softper.customerservice.resources.outputs.PersonOutput;
import com.softper.customerservice.resources.outputs.UserOutput;
import com.softper.customerservice.client.UserClient;
import com.softper.customerservice.exception.ResourceNotFoundException;
import com.softper.customerservice.models.Balance;
import com.softper.customerservice.models.Customer;
import com.softper.customerservice.models.Person;
import com.softper.customerservice.models.User;
import com.softper.customerservice.repositories.IBalanceRepository;
//import com.softper.customerservice.repositories.IBalanceRepository;
import com.softper.customerservice.repositories.ICustomerRepository;
//import com.softper.customerservice.repositories.IPersonRepository;
//import com.softper.customerservice.repositories.IUserRepository;
import com.softper.customerservice.services.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private IBalanceRepository balanceRepository;

    //@Autowired
    //IPersonRepository personRepository;

    //@Autowired
    //IUserRepository userRepository;

    //@Autowired
    //IBalanceRepository balanceRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Customer save(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(Integer id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public CustomerBoundResponse findCustomerById(int customerId) {
        try
        {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

            if(!optionalCustomer.isPresent()){
                CustomerBoundResponse response = new CustomerBoundResponse("findCustomerById", "not found",0);
                return response;
            }

            Customer  customer = optionalCustomer.get();
            CustomerBoundResponse response = new CustomerBoundResponse("findCustomerById", "success",1);
            CustomerOutput customerOutput = modelMapper.map(customer, CustomerOutput.class);

            response.setCustomerOutput(customerOutput);
            return response;
        }
        catch (Exception e)
        {
            return new CustomerBoundResponse("findCustomerById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public CustomerBoundResponse findAllCustomers() {
        try
        {
            List<Customer> customerList = customerRepository.findAll();
            List<CustomerOutput> customerOutputList = new ArrayList<>();
            for (Customer getCustomer:customerList) {
                UserBoundResponse getResponse = userClient.findPersonById(getCustomer.getPersonId()).getBody();
                CustomerOutput newCustomerOutput = modelMapper.map(getCustomer, CustomerOutput.class);
                newCustomerOutput.setCredits(getCustomer.getBalance().getAddedMoney());
                customerOutputList.add(newCustomerOutput);
            }
            CustomerBoundResponse response = new CustomerBoundResponse("findAllCustomers", "success", 1);
            response.setCustomerOutputs(customerOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new CustomerBoundResponse("findAllCustomers","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public CustomerBoundResponse rechargeCreditsByCustomerId(int customerId, double creditUnits) {
        try
        {
            Customer getCustomer = customerRepository.findById(customerId)
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));

            UserBoundResponse getResponse = userClient.findPersonById(getCustomer.getPersonId()).getBody();
            CustomerBoundResponse response = new CustomerBoundResponse("rechargeCreditsByCustomerId", "success", 1);


            CustomerOutput customerOutput = modelMapper.map(getCustomer, CustomerOutput.class);
            response.setCustomerOutput(customerOutput);
            return response;
        }
        catch (Exception e)
        {
            return new CustomerBoundResponse("rechargeCreditsByCustomerId","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public CustomerBoundResponse generateNewCustomer(int personId){
        try {

            Balance newBalance = new Balance();
            newBalance.setSpentMoney(0);
            newBalance.setAddedMoney(0);
    
            newBalance = balanceRepository.save(newBalance);
            Customer newCustomer = new Customer();
            newCustomer.setPersonId(personId);
            newCustomer.setCredits(0.0);
            newCustomer.setBalance(newBalance);

            newCustomer = customerRepository.save(newCustomer);

            CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse("generateNewCustomer","success",1);
            CustomerOutput customerOutput = modelMapper.map(newCustomer, CustomerOutput.class);
            customerBoundResponse.setCustomerOutput(customerOutput);
            return customerBoundResponse;
        }
        catch(Exception e)
        {
            return new CustomerBoundResponse("generateNewCustomer","Ocurrio un error : "+e.getMessage(),-2);
        }
    }

    @Override
    public CustomerBoundResponse findCustomerByPersonId(int personId)
    {
        try
        {
            Optional<Customer> optionalCustomer = customerRepository.findCustomerByPersonId(personId);

            if(!optionalCustomer.isPresent()){
                CustomerBoundResponse response = new CustomerBoundResponse("findCustomerByPersonId", "not found",0);
                response.setCustomerOutput(null);
                return response;
            }

            Customer  customer = optionalCustomer.get();
            CustomerBoundResponse response = new CustomerBoundResponse("findCustomerByPersonId", "success",1);
            CustomerOutput customerOutput = modelMapper.map(customer, CustomerOutput.class);

            response.setCustomerOutput(customerOutput);
            return response;
        }
        catch (Exception e)
        {
            return new CustomerBoundResponse("findCustomerByPersonId","An error ocurred : "+e.getMessage(),-2);
        }
    }
}