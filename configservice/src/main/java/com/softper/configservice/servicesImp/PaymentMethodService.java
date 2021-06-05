package com.softper.configservice.servicesImp;

import com.softper.configservice.repositories.IPaymentMethodRepository;
import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.PaymentMethodInput;
import com.softper.configservice.resources.outputs.PaymentMethodOutput;
import com.softper.configservice.services.IPaymentMethodService;
import com.softper.configservice.exception.ResourceNotFoundException;
import com.softper.configservice.models.PaymentMethod;
import com.softper.configservice.models.User;
//import com.softper.configservice.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    //@Autowired
    //IPaymentMethodRepository paymentMethodRepository;
    //@Autowired
    //IUserRepository userRepository;

    @Override
    public ConfigBoundResponse findAllPaymentMethod() {
        /*
        try
        {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
            List<PaymentMethodOutput> paymentMethodOutputList = new ArrayList<>();
            for (PaymentMethod p:paymentMethodList) {
                paymentMethodOutputList.add(new PaymentMethodOutput(p.getBankName(),
                        p.getSwiftCode(),p.getAccountNumber()));
            }
            return new PaymentMethodResponse(paymentMethodOutputList);
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
        */
        return null;
    }

    @Override
    public ConfigBoundResponse findPaymentMethodById(int paymentMethodId) {
        /*
        try
        {
            PaymentMethod getPaymentMethod = paymentMethodRepository.findById(paymentMethodId)
                    .orElseThrow(()->new ResourceNotFoundException("paymentMethod","id",paymentMethodId));

            return new PaymentMethodResponse(new PaymentMethodOutput(getPaymentMethod.getBankName(),
                    getPaymentMethod.getSwiftCode(),getPaymentMethod.getAccountNumber()));

        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public ConfigBoundResponse findPaymentMethodByUserId(int userId) {
        /*
        try
        {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findPaymentMethodByUserId(userId);
            List<PaymentMethodOutput> paymentMethodOutputList = new ArrayList<>();
            for (PaymentMethod p:paymentMethodList) {
                paymentMethodOutputList.add(new PaymentMethodOutput(p.getBankName(),
                        p.getSwiftCode(),p.getAccountNumber()));
            }

            return new PaymentMethodResponse(paymentMethodOutputList);
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }¨
        */
        return null;
    }

    @Override
    public ConfigBoundResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput) {
        /*
        try{
            User getUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("id","user",userId));
            PaymentMethod newPaymentMethod = new PaymentMethod();
            newPaymentMethod.setAccountNumber(paymentMethodInput.getAccountNumber());
            newPaymentMethod.setBankName(paymentMethodInput.getBankName());
            newPaymentMethod.setBillingAdress(paymentMethodInput.getBillingAdress());
            newPaymentMethod.setSwiftCode(paymentMethodInput.getSwiftCode());
            newPaymentMethod.setConfiguration(getUser.getConfiguration());

            newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

            return new PaymentMethodResponse(toPaymentMethodOutput(newPaymentMethod));
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception {
        //return paymentMethodRepository.save(paymentMethod);
        return null;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //paymentMethodRepository.deleteById(id);
    }

    @Override
    public Optional<PaymentMethod> findById(Integer id) throws Exception {
        //return paymentMethodRepository.findById(id);
        return null;

    }

    @Override
    public List<PaymentMethod> findAll() throws Exception {
        //return paymentMethodRepository.findAll();
        return null;

    }

    public PaymentMethodOutput toPaymentMethodOutput(PaymentMethod paymentMethod)
    {
        //return new PaymentMethodOutput(paymentMethod.getBankName(),
        //    paymentMethod.getSwiftCode(),paymentMethod.getAccountNumber());
        return null;

    }
}
