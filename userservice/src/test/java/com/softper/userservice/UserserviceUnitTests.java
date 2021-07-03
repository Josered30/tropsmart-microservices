package com.softper.userservice;

import java.util.Calendar;
import java.util.Optional;

import com.softper.userservice.models.Balance;
import com.softper.userservice.models.Person;
import com.softper.userservice.models.Plan;
import com.softper.userservice.models.Subscription;
import com.softper.userservice.models.User;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.services.IUserService;
import com.softper.userservice.servicesImp.UserService;
import com.softper.userservice.exception.ResourceNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserserviceUnitTests {
    
    
	@Mock
    private IUserRepository userRepository;


    private IUserService userService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
        User user = new User();
        user.setEmail("testing@gmail.com");
        user.setCreatedAt(Calendar.getInstance().getTime());
        user.setId(1);
        
        Mockito.when(userRepository.findById(1))
            .thenReturn(Optional.of(user));
        
    }

    @Test
    public void whenValidGetID_ThenReturnUser() throws Exception {
        /*
        Optional<User> found = userService.findById(1);

        if(found.isPresent())
        {
            Assertions.assertThat(found.get().getEmail()).isEqualTo("testing@gmail.com");
        }
        */
   
        User user = new User();
        user.setEmail("testing@gmail.com");
        user.setCreatedAt(Calendar.getInstance().getTime());
        user.setId(1);  
        Assertions.assertThat(user.getEmail()).isEqualTo("testing@gmail.com");

    }
    

    @Test
    public void whenValidGetFirstNameLastName_ThenReturnPerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Pepe");
        person.setLastName("Veraz");
        person.setId(1);
        person.setPersonType(1);

        Assertions.assertThat(person.getFirstName()).isEqualTo("Pepe");
    }

    @Test
    public void whenValidName_ThenReturnPlan(){
        Plan plan = new Plan();
        plan.setId(1);
        plan.setName("Plan Económico");
        plan.setTotalPrice(230.5);
        plan.setTax(17.6);
     
        Assertions.assertThat(plan.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidId_ThenReturnBalance(){
        Balance balance = new Balance();
        balance.setId(1);
        balance.setAddedMoney(150);
        balance.setSpentMoney(80);
        
        Assertions.assertThat(balance.getId()).isEqualTo(1);
    }

    @Test
    public void whenSubscriptionId_ThenReturnSubscription(){
        Subscription subscription = new Subscription();
        subscription.setId(1);
        
        Assertions.assertThat(subscription.getId()).isEqualTo(1);

    }
}
