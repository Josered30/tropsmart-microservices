package com.softper.userservice.servicesImp;

import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.client.DriverClient;
import com.softper.userservice.models.Person;
import com.softper.userservice.models.User;
import com.softper.userservice.repositories.IPersonRepository;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.resources.outputs.PersonOutput;
import com.softper.userservice.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    IPersonRepository personRepository;
    
    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private DriverClient driverClient;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }


    @Override
    public UserBoundResponse findPeopleById(int id) {
        
        try
        {
            UserBoundResponse response;
            User getUser = userRepository.findUserByPersonId(id).get();
            Person getPerson = personRepository.findById(id).get();

            PersonOutput newPersonOutput = new PersonOutput();
            newPersonOutput.setEmail(getUser.getEmail());
            newPersonOutput.setFirstName(getPerson.getFirstName());
            newPersonOutput.setLastName(getPerson.getLastName());
            if(getPerson.getPersonType()==1)
                newPersonOutput.setUserType("Customer");
            if(getPerson.getPersonType()==2)
                newPersonOutput.setUserType("Driver");
            response = new UserBoundResponse("findPeopleById","success",1);
            response.setPersonOutput(newPersonOutput);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findPeopleById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public UserBoundResponse findAllPersons() {
        
        try
        {
            UserBoundResponse response;
            List<Person> personList = personRepository.findAll();
            List<PersonOutput> personOutputList = new ArrayList<>();
            for (Person p:personList) {
                Optional<User> getUser = userRepository.findUserByPersonId(p.getId());
                PersonOutput newPersonOutput = new PersonOutput();
                newPersonOutput.setEmail(getUser.get().getEmail());
                newPersonOutput.setFirstName(p.getFirstName());
                newPersonOutput.setLastName(p.getLastName());
                if(p.getPersonType()==1)
                    newPersonOutput.setUserType("Customer");
                if(p.getPersonType()==2)
                    newPersonOutput.setUserType("Driver");
                personOutputList.add(newPersonOutput);
            }

            response = new UserBoundResponse("findAllPersons","success",1);
            response.setPersonOutputs(personOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("findAllPersons", "An error ocurred : "+e.getMessage(),-2);
        }
        
    }
}
