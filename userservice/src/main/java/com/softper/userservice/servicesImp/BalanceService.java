package com.softper.userservice.servicesImp;

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

    //@Autowired
    //private ICustomerRepository customerRepository;

    @Override
    public UserBoundResponse findBalanceById(int balanceId) {
        /*
        try {
            User getUser = userRepository.findUserByBalanceId(balanceId)
                    .orElseThrow(()-> new ResourceNotFoundException("user","id",balanceId));

            Balance getBalance = getUser.getBalance();

            return new BalanceResponse(new BalanceOutput(getUser.getPerson().getFirstName()+" "+getUser.getPerson().getLastName(),getUser.getEmail(),getUser.getPerson().getCustomer().getCredits(),getBalance.getAddedMoney(),getBalance.getSpentMoney()));
        }
        catch(Exception e)
        {
            return new BalanceResponse("An error ocurred while getting balance: "+e.getMessage());
        }
        */
        return null;

    }

    @Override
    public UserBoundResponse findAllBalances() {
        /*try
        {
            List<Balance> balanceList = balanceRepository.findAll();
            List<BalanceOutput> balanceOutputList = new ArrayList<>();
            for (Balance b:balanceList) {
                User getUser = userRepository.findUserByBalanceId(b.getId())
                        .orElseThrow(()-> new ResourceNotFoundException("user","id",b.getId()));
                balanceOutputList.add(new BalanceOutput(getUser.getPerson().getFirstName()+" "+getUser.getPerson().getLastName(),getUser.getEmail(),getUser.getPerson().getCustomer().getCredits(),b.getAddedMoney(),b.getSpentMoney()));
            }
            return new BalanceResponse(balanceOutputList);
        }
        catch (Exception e)
        {
            return new BalanceResponse("An error ocurred while getting balance list: "+e.getMessage());
        }
        */
        return null;

    }

    @Override
    public Balance save(Balance balance) throws Exception {
        //return balanceRepository.save(balance);
        return null;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //balanceRepository.deleteById(id);
    }

    @Override
    public Optional<Balance> findById(Integer id) throws Exception {
        //return balanceRepository.findById(id);
        return null;
    }

    @Override
    public List<Balance> findAll() throws Exception {
        //return balanceRepository.findAll();
        return null;
    }
}
