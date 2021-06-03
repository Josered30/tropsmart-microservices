package com.softper.customerservice.servicesImp;

import com.softper.customerservice.resources.comunications.CustomerBoundResponse;
import com.softper.customerservice.resources.outputs.CustomerOutput;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    //@Autowired
    //IPersonRepository personRepository;

    //@Autowired
    //IUserRepository userRepository;

    //@Autowired
    //IBalanceRepository balanceRepository;

    @Override
    public Customer save(Customer customer) throws Exception {
        //return customerRepository.save(customer);
        return null;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(Integer id) throws Exception {
        //return customerRepository.findById(id);
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        //return customerRepository.findAll();
        return null;
    }

    @Override
    public CustomerBoundResponse findCustomerById(int customerId) {
        /*try
        {
            Customer getCustomer = customerRepository.findById(customerId).get();
            return new CustomerResponse(new CustomerOutput(getCustomer.getPerson().getUser().getId(),getCustomer.getPerson().getFirstName(),getCustomer.getPerson().getLastName(),getCustomer.getCredits(),getCustomer.getPerson().getUser().getEmail(), getCustomer.getPerson().getPersonType(),getCustomer.getId()));
        }
        catch (Exception e)
        {
            return new CustomerResponse("An error ocurred while getting customer: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CustomerBoundResponse findAllCustomers() {
        /*try
        {
            List<Customer> customerList = customerRepository.findAll();
            List<CustomerOutput> customerOutputList = new ArrayList<>();
            for (Customer getCustomer:customerList) {
                Person getPerson = personRepository.findById(getCustomer.getId()).get();
                customerOutputList.add(new CustomerOutput(getCustomer.getPerson().getUser().getId(),getPerson.getFirstName(),getPerson.getLastName(),getCustomer.getCredits(),getCustomer.getPerson().getUser().getEmail(), getCustomer.getPerson().getPersonType(),getCustomer.getId()));
            }
            return new CustomerResponse(customerOutputList);
        }
        catch (Exception e)
        {
            return new CustomerResponse("An error ocurred while getting customer list: "+e.getMessage());
        }
        */
        return null;
    }

    @Override
    public CustomerBoundResponse rechargeCreditsByCustomerId(int customerId, double creditUnits) {
        /*try
        {
            Customer getCustomer = customerRepository.findById(customerId)
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));
            User getUser = userRepository.findUserByPersonId(getCustomer.getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","personId",customerId));
            Balance getBalance = getUser.getBalance();

            getBalance.rechargeMoney(creditUnits);

            getBalance = balanceRepository.save(getBalance);

            getCustomer.setCredits(getCustomer.getCredits()+creditUnits);

            getCustomer = customerRepository.save(getCustomer);

            return new CustomerResponse(new CustomerOutput(getCustomer.getPerson().getUser().getId(),getCustomer.getPerson().getFirstName(),getCustomer.getPerson().getLastName(),getCustomer.getCredits(),getCustomer.getPerson().getUser().getEmail(), getCustomer.getPerson().getPersonType(),getCustomer.getId()));

        }
        catch (Exception e)
        {
            return new CustomerResponse("An error ocurred while rechart credits on user"+e.getMessage());
        }*/
        return null;
    }

    @Override
    public Customer generateNewCustomer(int personId){
        try {
            Customer newCustomer = new Customer();
            newCustomer.setPersonId(personId);
            newCustomer.setCredits(0.0);

            newCustomer = customerRepository.save(newCustomer);
            //CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse("generateNewCustomer",null,1);
            //customerBoundResponse.setCustomerOutput(toCustomerOutput(newCustomer));
            //return customerBoundResponse;
            return newCustomer;
        }
        catch(Exception e)
        {
            //return new CustomerBoundResponse("generateNewCustomer","Ocurrio un error : "+e.getMessage(),-2);
            return null;
        }
    }

    public CustomerOutput toCustomerOutput(Customer getCustomer) {
        CustomerOutput customerOutput = new CustomerOutput();
        customerOutput.setCredits(getCustomer.getCredits());
        customerOutput.setId(getCustomer.getId());

        return customerOutput;
    }

}