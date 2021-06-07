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
//import com.softper.customerservice.repositories.IBalanceRepository;
import com.softper.customerservice.repositories.ICustomerRepository;
//import com.softper.customerservice.repositories.IPersonRepository;
//import com.softper.customerservice.repositories.IUserRepository;
import com.softper.customerservice.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    UserClient userClient;

    
    //@Autowired
    //IPersonRepository personRepository;

    //@Autowired
    //IUserRepository userRepository;

    //@Autowired
    //IBalanceRepository balanceRepository;

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
            Customer getCustomer = customerRepository.findById(customerId).get();
            UserBoundResponse getPerson = userClient.getPersonModelById(getCustomer.getPersonId()).getBody();
            //BalanceOutput getBalance = userClient.findBalanceByCustomerId(getCustomer.getId()).getBody().getBalanceOutput();
           
            CustomerBoundResponse response = new CustomerBoundResponse("findCustomerById", "success",1);
            //response.setCustomerOutput(toCustomerOutput(getCustomer, getResponse.getPersonOutput()));
            //response.getCustomerOutput().setCredits(getCustomer.getBalance().getAddedMoney());
            response.setCustomerOutput(toCustomerModelOutput(getCustomer));
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
                UserBoundResponse getResponse = userClient.getPersonModelById(getCustomer.getPersonId()).getBody();
                CustomerOutput newCustomerOutput = toCustomerOutput(getCustomer, getResponse.getPersonOutput());
                //newCustomerOutput.setCredits(getCustomer.getBalance().getAddedMoney());
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
            Customer getCustomer = customerRepository.findById(customerId).get();
            //UserBoundResponse getPerson = userClient.getPersonModelById((getCustomer.getPersonId())).getBody();

            UserBoundResponse getRecharged = userClient.rechargeMoneyByPersonId(getCustomer.getPersonId(), creditUnits).getBody();

            
            CustomerBoundResponse response = new CustomerBoundResponse("rechargeCreditsByCustomerId", "success", 1);
            response.setCustomerOutput(toCustomerModelOutput(getCustomer));
            //response.setCustomerOutput(toCustomerOutput(getCustomer, getPerson.getPersonOutput()));
            //response.getCustomerOutput().setCredits(getCustomer.getBalance().getAddedMoney());
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

            //Balance newBalance = new Balance();
            //newBalance.setSpentMoney(0);
            //newBalance.setAddedMoney(0);
    
            //newBalance = balanceRepository.save(newBalance);
            Customer newCustomer = new Customer();
            newCustomer.setPersonId(personId);
            newCustomer.setCredits(0.0);
            //newCustomer.setBalance(newBalance);

            newCustomer = customerRepository.save(newCustomer);

            CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse("generateNewCustomer","success",1);
            customerBoundResponse.setCustomerOutput(toCustomerModelOutput(newCustomer));
            return customerBoundResponse;
        }
        catch(Exception e)
        {
            //return new CustomerBoundResponse("generateNewCustomer","Ocurrio un error : "+e.getMessage(),-2);
            return null;
        }
    }

    @Override
    public Customer findCustomerByPersonId(int personId)
    {
        try{
            return customerRepository.findCustomerByPersonId(personId);
        }catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public Customer getCustomerById(int customerId)
    {
        try{
            return customerRepository.findById(customerId).get();
        }catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public CustomerBoundResponse getCustomerModelById(int customerId){
        try{
            Optional<Customer> getCustomer = customerRepository.findById(customerId);
            if(getCustomer.isPresent())
            {
                CustomerBoundResponse response = new CustomerBoundResponse("getCustomerModelById","success",1);
                response.setCustomerOutput(toCustomerModelOutput(getCustomer.get()));
                return response;
            }else{
                return new CustomerBoundResponse("getCustomerModelById","not found", 0);
            }
        }catch(Exception e)
        {
            return new CustomerBoundResponse("getCustomerModelById","An error ocurred : "+e.getMessage(),-2);
        }
    }


    
    public CustomerOutput toCustomerOutput(Customer getCustomer, PersonOutput getPersonOutput) {
        CustomerOutput customerOutput = new CustomerOutput();
        customerOutput.setCredits(getCustomer.getCredits());
        customerOutput.setId(getCustomer.getId());
        customerOutput.setEmail(getPersonOutput.getEmail());
        customerOutput.setFirstName(getPersonOutput.getFirstName());
        customerOutput.setLastName(getPersonOutput.getLastName());

        return customerOutput;
    }
    

    public CustomerOutput toCustomerModelOutput(Customer getCustomer){
        CustomerOutput newCustomerOutput = new CustomerOutput();
        newCustomerOutput.setCredits(getCustomer.getCredits());
        newCustomerOutput.setId(getCustomer.getId());
        return newCustomerOutput;
    }





}