package com.softper.userservice;


import io.cucumber.junit.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.servicesImp.UserService;

@CucumberContextConfiguration
@SpringBootTest(classes = UserserviceApplicationTests.class)
public class CucumberSpringConfiguration {

   
}
