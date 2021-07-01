package com.softper.customerservice.servicesImp;

import com.softper.customerservice.client.UserClient;
import com.softper.customerservice.exception.ResourceNotFoundException;
import com.softper.customerservice.models.Balance;
import com.softper.customerservice.models.Customer;
import com.softper.customerservice.models.User;
import com.softper.customerservice.repositories.IBalanceRepository;
import com.softper.customerservice.repositories.ICustomerRepository;
import com.softper.customerservice.resources.comunications.CustomerBoundResponse;
//import com.softper.customerservice.repositories.IUserRepository;
import com.softper.customerservice.resources.comunications.UserBoundResponse;
import com.softper.customerservice.resources.outputs.BalanceOutput;
import com.softper.customerservice.resources.outputs.CustomerOutput;
import com.softper.customerservice.resources.outputs.PersonOutput;
import com.softper.customerservice.services.IBalanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService implements IBalanceService {
    @Autowired
    private IBalanceRepository balanceRepository;

    //@Autowired
    //private IUserRepository userRepository;
    @Autowired
    private UserClient userClient;

    @Autowired
    private ICustomerRepository customerRepository;
    //@Autowired
    //private CustomerClient customerClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerBoundResponse findBalanceById(int balanceId) {

        try {
            Optional<Balance> getBalance = balanceRepository.findById(balanceId);
            Optional<Customer> getCustomer = customerRepository.findCustomerByBalanceId(balanceId);

            if (!getCustomer.isPresent() || !getBalance.isPresent()) {
                return new CustomerBoundResponse("findBalanceById", "not found", 0);
            }

            CustomerBoundResponse response = new CustomerBoundResponse("findBalanceById", "success", 1);
            BalanceOutput balanceOutput = modelMapper.map(getBalance, BalanceOutput.class);
            CustomerOutput customerOutput = modelMapper.map(getCustomer, CustomerOutput.class);

            response.setBalanceOutput(balanceOutput);
            response.setCustomerOutput(customerOutput);
            return response;

        } catch (Exception e) {
            return new CustomerBoundResponse("findBalanceById", "An error ocurred : " + e.getMessage(), -2);
        }

    }

    @Override
    public CustomerBoundResponse findAllBalances() {
        try {
            List<Balance> balanceList = balanceRepository.findAll();
            List<BalanceOutput> balanceOutputList = new ArrayList<>();
            for (Balance b : balanceList) {
                Optional<Customer> getCustomer = customerRepository.findCustomerByBalanceId(b.getId());
                getCustomer.ifPresent(customer -> balanceOutputList.add(modelMapper.map(customer.getBalance(), BalanceOutput.class)));
            }
            CustomerBoundResponse response = new CustomerBoundResponse("findAllBalances", "success", 1);
            response.setBalanceOutputs(balanceOutputList);
            return response;
            //return new BalanceResponse(balanceOutputList);
        } catch (Exception e) {
            return new CustomerBoundResponse("findAllBalances", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    /*
    @Override
    public CustomerBoundResponse findBalanceByUserId(int userId)
    {
        try{
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("userId","userId",userId));
            CustomerOutput getCustomer = new CustomerOutput();
            if(getUser.getPerson().getCustomerId() != null)
            {
                getCustomer = customerClient.findCustomersById(getUser.getPerson().getCustomerId()).getBody().getCustomerOutput();
            }
            UserBoundResponse response = new UserBoundResponse("findBalanceByUserId","success",1);
            response.setBalanceOutput(toBalanceOutput(getUser.getBalance(),getCustomer));
            return response;
        } catch(Exception e)
        {
            return new UserBoundResponse("findBalanceByUserId","An error ocurred : "+e.getMessage(),-2);
        }
    }
    */

    /*
    @Override
    public CustomerBoundResponse rechargeCreditsByUserId(int userId, double money)
    {
        try{
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("userId","userId",userId));
            CustomerOutput getCustomer = customerClient.findCustomersById(getUser.getPerson().getCustomerId()).getBody().getCustomerOutput();
            Balance getBalance = getUser.getBalance();
            getBalance.rechargeMoney(money);

            getBalance = balanceRepository.save(getBalance);
            UserBoundResponse response = new UserBoundResponse("rechargeCreditsByUserId","success",1);
            response.setBalanceOutput(toBalanceOutput(getBalance, getCustomer));
            return response;
        }catch(Exception e)
        {
            return new UserBoundResponse("rechargeCreditsByUserId","An error ocurred : "+e.getMessage(),-2);
        }
    }
    */

    /*
    @Override
    public CustomerBoundResponse findBalanceByPersonId(int personId)
    {
        try{
            
            User getUser = userRepository.findUserByPersonId(personId).get();
            CustomerOutput getCustomer = customerClient.findCustomersById(getUser.getPerson().getCustomerId()).getBody().getCustomerOutput();
            UserBoundResponse response = new UserBoundResponse("findBalanceByCustomerId","success",1);
            response.setBalanceOutput(toBalanceOutput(getUser.getBalance(), null));
            return response;
        } catch(Exception e)
        {
            return new UserBoundResponse("findBalanceByCustomerId","An error ocurred : "+e.getMessage(),-2);
        }
    }
    */

    @Override
    public Balance save(Balance balance) throws Exception {
        return balanceRepository.save(balance);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        balanceRepository.deleteById(id);
    }

    @Override
    public Optional<Balance> findById(Integer id) throws Exception {
        return balanceRepository.findById(id);
    }

    @Override
    public List<Balance> findAll() throws Exception {
        return balanceRepository.findAll();
    }

}
