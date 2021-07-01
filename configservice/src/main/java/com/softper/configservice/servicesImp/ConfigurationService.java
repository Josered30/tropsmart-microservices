package com.softper.configservice.servicesImp;

import com.softper.configservice.repositories.IConfigurationRepository;
import com.softper.configservice.repositories.IPaymentMethodRepository;
import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.ConfigurationInput;
import com.softper.configservice.resources.inputs.PaymentMethodInput;
import com.softper.configservice.resources.outputs.ConfigurationOutput;
import com.softper.configservice.services.IConfigurationService;
import com.softper.configservice.models.Configuration;
import com.softper.configservice.models.PaymentMethod;
import com.softper.configservice.models.Person;
import com.softper.configservice.models.User;
//import com.softper.configservice.repositories.IPersonRepository;
//import com.softper.configservice.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService implements IConfigurationService {

    @Autowired
    IConfigurationRepository configurationRepository;

    //@Autowired
    //IUserRepository userRepository;

    //@Autowired
    //IPersonRepository personRepository;

    @Autowired
    IPaymentMethodRepository paymentMethodRepository;





    @Override
    public ConfigBoundResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput) {
        try
        {
            /*
            User getUser = userRepository.findById(userId).get();
            PaymentMethod newPaymentMethod = new PaymentMethod();
            newPaymentMethod.setAccountNumber(paymentMethodInput.getAccountNumber());
            newPaymentMethod.setBankName(paymentMethodInput.getBankName());
            newPaymentMethod.setBillingAdress(paymentMethodInput.getBillingAdress());
            newPaymentMethod.setSwiftCode(paymentMethodInput.getSwiftCode());
            newPaymentMethod.setConfiguration(getUser.getConfiguration());

            newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

            ConfigurationOutput getConfigurationOutput = new ConfigurationOutput();
            getConfigurationOutput.setFirstName(getUser.getPerson().getFirstName());
            getConfigurationOutput.setLastName(getUser.getPerson().getLastName());
            getConfigurationOutput.setLanguage(getUser.getConfiguration().getLanguage().toString());
            getConfigurationOutput.setPaymentCurrency(getUser.getConfiguration().getPaymentCurrency().toString());
            getConfigurationOutput.setPhone(getUser.getPerson().getPhone());
            return new ConfigurationResponse(getConfigurationOutput);
            */
            return null;
        }
        catch (Exception e)
        {
            return new ConfigBoundResponse("addPaymentMethod","An error ocurred while getting a configuration "+e.getMessage(),2);
        }
    }


    @Override
    public ConfigBoundResponse findAllConfigurations() {
        try
        {
            /*
            List<Configuration> configurationList = configurationRepository.findAll();
            List<ConfigurationOutput> configurationOutputList = new ArrayList<>();
            for(Configuration c:configurationList)
            {
                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setFirstName(c.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(c.getUser().getPerson().getLastName());
                newConfigurationOutput.setLanguage(c.getLanguage().toString());
                newConfigurationOutput.setPhone(c.getUser().getPerson().getPhone());
                newConfigurationOutput.setPaymentCurrency(c.getPaymentCurrency().toString());
                configurationOutputList.add(newConfigurationOutput);
            }
            return new ConfigurationResponse(configurationOutputList);
            */
            return null;
        }
        catch (Exception e)
        {
            return new ConfigBoundResponse("findAllConfigurations","An error ocurred while getting a configuration "+e.getMessage(),2);
        }
    }


    @Override
    public ConfigBoundResponse findConfigurationByUserId(int userId) {
        try
        {
            /*
            User getUser = userRepository.findById(userId).get();
            ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
            newConfigurationOutput.setFirstName(getUser.getPerson().getFirstName());
            newConfigurationOutput.setLastName(getUser.getPerson().getLastName());
            newConfigurationOutput.setPaymentCurrency(getUser.getConfiguration().getPaymentCurrency().toString());
            newConfigurationOutput.setLanguage(getUser.getConfiguration().getLanguage().toString());
            newConfigurationOutput.setPhone(getUser.getPerson().getPhone());
            return new ConfigurationResponse(newConfigurationOutput);
            */
            return null;
        }
        catch (Exception e)
        {
            return new ConfigBoundResponse("findConfigurationByUserId","An error ocurred while getting a configuration "+e.getMessage(),2);
        }
    }

    @Override
    public ConfigBoundResponse updateConfiguration(int userId, ConfigurationInput configurationInput) {
        try
        {
            /*
            User getUser = userRepository.findById(userId).get();
            Person getPerson = getUser.getPerson();
            Configuration getConfiguration = getUser.getConfiguration();
            getConfiguration.setLanguage(configurationInput.getLanguage());
            getConfiguration.setPaymentCurrency(configurationInput.getPaymentCurrency());
            getPerson.setFirstName(configurationInput.getFirstName());
            getPerson.setLastName(configurationInput.getLastName());
            getPerson.setPhone(configurationInput.getPhone());
            getPerson = personRepository.save(getPerson);
            getConfiguration = configurationRepository.save(getConfiguration);

            ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
            newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
            newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
            newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
            newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
            newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());

            return new ConfigurationResponse(newConfigurationOutput);
            */
            return null;
        }
        catch (Exception e)
        {
            return new ConfigBoundResponse("updateConfiguration","An error ocurred while getting a configuration "+e.getMessage(),2);
        }
    }

    @Override
    public ConfigBoundResponse generateConfiguration(int userId) {
        try{
            /*
            User getUser = userRepository.findById(userId).get();
            if(getUser.getConfiguration()== null)
            {
                Configuration newConfiguration = new Configuration();
                newConfiguration.setLanguage("Spanish");
                newConfiguration.setPaymentCurrency("Soles");

                newConfiguration = configurationRepository.save(newConfiguration);

                getUser.setConfiguration(newConfiguration);
                getUser = userRepository.save(getUser);

                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setPaymentCurrency(newConfiguration.getPaymentCurrency());
                newConfigurationOutput.setLanguage(newConfiguration.getLanguage());
                newConfigurationOutput.setPhone(newConfiguration.getUser().getPerson().getPhone());
                newConfigurationOutput.setFirstName(newConfiguration.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(newConfiguration.getUser().getPerson().getLastName());
                return new ConfigurationResponse(newConfigurationOutput);

            }
            else{
                Configuration getConfiguration = getUser.getConfiguration();
                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
                newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
                newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
                newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());

                return new ConfigurationResponse(newConfigurationOutput);
            }
            */
            return null;
        }catch (Exception e)
        {
            return new ConfigBoundResponse("generateConfiguration","An error ocurred while getting a configuration "+e.getMessage(),2);
        }
    }

    @Override
    public Configuration save(Configuration configuration) throws Exception {
        //return configurationRepository.save(configuration);
        return null;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //configurationRepository.deleteById(id);
    }

    @Override
    public Optional<Configuration> findById(Integer id) throws Exception {
        //return configurationRepository.findById(id);
        return null;
    }

    @Override
    public List<Configuration> findAll() throws Exception {
        //return configurationRepository.findAll();
        return null;
    }

    @Override
    public ConfigBoundResponse generateConfiguration(){
        try{

            Configuration newConfiguration = new Configuration();
            newConfiguration.setLanguage("Spanish");
            newConfiguration.setPaymentCurrency("Soles");
    
            newConfiguration = configurationRepository.save(newConfiguration);
            ConfigBoundResponse configuraiBoundedResponse = new ConfigBoundResponse("GenerateConfiguration", null,1);
            configuraiBoundedResponse.setConfigurationOutput(toConfigurationOutput(newConfiguration));
            return configuraiBoundedResponse;

        }catch(Exception e)
        {
            return new ConfigBoundResponse("GenerateConfiguration","An error ocurred while getting a configuration "+e.getMessage(),-2);
        }
    }

    public ConfigurationOutput toConfigurationOutput(Configuration getConfiguration) {
        ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
        newConfigurationOutput.setId(getConfiguration.getId());
        newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
        newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
        //newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
        //newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
        //newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());
        return newConfigurationOutput;
    }
}
