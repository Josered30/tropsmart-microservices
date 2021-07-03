package com.softper.userservice.servicesImp;

import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.exception.ResourceNotFoundException;
import com.softper.userservice.models.Balance;
import com.softper.userservice.models.User;
import com.softper.userservice.repositories.IBalanceRepository;
//import com.softper.userservice.repositories.ICustomerRepository;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.resources.outputs.BalanceOutput;
import com.softper.userservice.services.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService implements IBalanceService {
    @Autowired
    private IBalanceRepository balanceRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private CustomerClient customerClient;
    //private ICustomerRepository customerRepository;


    @Override
    public UserBoundResponse findBalanceById(int balanceId) {
        
        try {
            Optional<Balance> getBalance = balanceRepository.findById(balanceId);
            if(getBalance.isPresent())
            {
                UserBoundResponse response = new UserBoundResponse("findBalanceById","success",1);
                response.setBalanceOutput(toBalanceModelOutput(getBalance.get()));
                return response;
            }else {
                return new UserBoundResponse("findBalanceById","Not found",0);
            }
        }
        catch(Exception e)
        {
            return new UserBoundResponse("findBalanceById","An error ocurred : "+e.getMessage(),-2);
        }
        
    }

    @Override
    public UserBoundResponse findAllBalances() {
        
        try
        {
            List<Balance> balanceList = balanceRepository.findAll();
            List<BalanceOutput> balanceOutputList = new ArrayList<>();
            for (Balance b:balanceList) {
                User getUser = userRepository.findUserByBalanceId(b.getId())
                        .orElseThrow(()-> new ResourceNotFoundException("user","id",b.getId()));
                balanceOutputList.add(new BalanceOutput(getUser.getPerson().getFirstName()+" "+getUser.getPerson().getLastName(),getUser.getEmail(),getUser.getPerson().getCustomer().getCredits(),b.getAddedMoney(),b.getSpentMoney()));
            }
            UserBoundResponse response = new UserBoundResponse("findAllBalances","success",1);
            response.setBalanceOutputs(balanceOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllBalances","An error ocurred : "+e.getMessage(),-2);
    
        }
    }

    @Override
    public UserBoundResponse getBalanceModelById(int balanceId)
    {
        try {
            Optional<Balance> getBalance = balanceRepository.findById(balanceId);
            if(getBalance.isPresent())
            {
                UserBoundResponse response = new UserBoundResponse("findBalanceById","success",1);
                response.setBalanceOutput(toBalanceModelOutput(getBalance.get()));
                return response;
            }else {
                return new UserBoundResponse("findBalanceById","Not found",0);
            }
        }
        catch(Exception e)
        {
            return new UserBoundResponse("findBalanceById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse rechargeCreditsByPersonId(int personId, double credits)
    {   
        try{
            Optional<User> getUser = userRepository.findUserByPersonId(personId);
            if(getUser.isPresent())
            {
                Balance getBalance = getUser.get().getBalance();
                getBalance.setAddedMoney(getBalance.getAddedMoney()+credits);

                UserBoundResponse response = new UserBoundResponse("rechargeCreditsByPersonId","success",1);
                response.setBalanceOutput(toBalanceModelOutput(getBalance));
                return response;
            }else {
                return new UserBoundResponse("rechargeCreditsByPersonId", "Not found",0);
            }
        } catch(Exception e)
        {
            return new UserBoundResponse("rechargeCreditsByPersonId","An error ocurred : "+e.getMessage(),-2);

        }
    }

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

    public BalanceOutput toBalanceModelOutput(Balance getBalance){
        BalanceOutput newBalanceOutput = new BalanceOutput();
        newBalanceOutput.setId(getBalance.getId());
        newBalanceOutput.setAddedMoney(getBalance.getAddedMoney());
        newBalanceOutput.setSpentMoney(getBalance.getSpentMoney());
        return newBalanceOutput;
    }
}