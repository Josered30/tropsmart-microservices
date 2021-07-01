package com.softper.userservice.servicesImp;

import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.client.DriverClient;
import com.softper.userservice.models.Person;
import com.softper.userservice.repositories.IPersonRepository;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.services.IPersonService;
import com.tropsmart.resources.comunications.UserBoundResponse;
import com.tropsmart.resources.outputs.PersonOutput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("com.softper.userservice.client.CustomerClient")
    private CustomerClient customerClient;

    @Autowired
    @Qualifier("com.softper.userservice.client.DriverClient")
    private DriverClient driverClient;

    @Autowired
    private ModelMapper modelMapper;

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

        try {
            Optional<Person> person = personRepository.findById(id);

            if (!person.isPresent()) {
                return new UserBoundResponse("findPeopleById", "not found", 0);
            }

            UserBoundResponse response = new UserBoundResponse("findPeopleById", "success", 1);
            response.setPersonOutput(modelMapper.map(person.get(), PersonOutput.class));
            return response;

        } catch (Exception e) {
            return new UserBoundResponse("findPeopleById", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse findAllPersons() {

        try {
            UserBoundResponse response;
            List<Person> personList = personRepository.findAll();
            List<PersonOutput> personOutputList = new ArrayList<>();
            for (Person p : personList) {
                personOutputList.add(modelMapper.map(p, PersonOutput.class));
            }

            response = new UserBoundResponse("findAllPersons", "success", 1);
            response.setPersonOutputs(personOutputList);
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("findAllPersons", "An error ocurred : " + e.getMessage(), -2);
        }

    }

    @Override
    public Person getPersonById(int personId) {
        try {
            return personRepository.findById(personId).get();

        } catch (Exception e) {
            return null;
        }
    }

}
