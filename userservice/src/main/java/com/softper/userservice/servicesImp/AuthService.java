package com.softper.userservice.servicesImp;


import com.softper.userservice.client.ConfigurationClient;
import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.client.DriverClient;
import com.softper.userservice.exception.ResourceNotFoundException;
import com.softper.userservice.models.*;
import com.softper.userservice.repositories.*;
import com.softper.userservice.resources.inputs.SignUp;
import com.softper.userservice.security.JwtProvider;
import com.softper.userservice.services.IAuthService;
import com.tropsmart.resources.comunications.ConfigBoundResponse;
import com.tropsmart.resources.comunications.CustomerBoundResponse;
import com.tropsmart.resources.comunications.UserBoundResponse;
import com.tropsmart.resources.outputs.AuthenticatedOutput;
import com.tropsmart.resources.outputs.UserOutput;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.validator.cfg.GenericConstraintDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthService implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    @Qualifier("com.softper.userservice.client.CustomerClient")
    private CustomerClient customerClient;

    @Autowired
    @Qualifier("com.softper.userservice.client.DriverClient")
    private DriverClient driverClient;

    @Autowired
    @Qualifier("com.softper.userservice.client.ConfigurationClient")
    private ConfigurationClient configurationClient;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserBoundResponse registerComplete(SignUp signUp) {

        try {
            UserBoundResponse response = new UserBoundResponse();
            Optional<User> result = userRepository.findByEmail(signUp.getEmail());
            if (result.isPresent()) {
                return new UserBoundResponse("registerComplete", "El correo : " + result.get().getEmail() + " ya se encuentra registrado", 0);
            } else {
                logger.info("Correo no registrado");

                Person newPerson = new Person();
                newPerson.setFirstName(signUp.getFirstName());
                newPerson.setLastName(signUp.getLastName());
                newPerson.setPhone(signUp.getPhone());
                newPerson.setPersonType(signUp.getDiscriminator());
                newPerson = personRepository.save(newPerson);

                //Configuration newConfiguration = new Configuration();
                //newConfiguration.setLanguage("Spanish");
                //newConfiguration.setPaymentCurrency("Soles");

                //newConfiguration = configurationRepository.save(newConfiguration);
                ConfigBoundResponse configurationResponse = configurationClient.generateConfiguration().getBody();

                if (configurationResponse == null || configurationResponse.getStatus() == -2) {
                    return new UserBoundResponse("registerComplete", "server error", -2);
                }

                if (configurationResponse.getStatus() == -1) {
                    return new UserBoundResponse("registerComplete", "fallback error", -1);
                }


                //Balance newBalance = new Balance();
                //newBalance.setSpentMoney(0);
                //newBalance.setAddedMoney(0);

                //newBalance = balanceRepository.save(newBalance);


                User user = new User();
                user.setEmail(signUp.getEmail());
                user.setPerson(newPerson);


                //user.setPassword(encoder.encode((signUp.getPassword())));
                user.setPassword(signUp.getPassword());
                user.setCreatedAt(Calendar.getInstance().getTime());
                //user.setConfiguration(newConfiguration);
                user.setConfigurationId(configurationResponse.getConfigurationOutput().getId());
                //user.setBalance(newBalance);

                user = userRepository.save(user);


                if (signUp.getDiscriminator() == 1) {
                    //Customer newCustomer = new Customer();
                    //newCustomer.setCredits(0.0);
                    //newCustomer.setPerson(newPerson);

                    //newPerson.setCustomer(newCustomer);
                    //customerRepository.save(newCustomer);
                    CustomerBoundResponse customerBoundResponse = customerClient.generateNewCustomer(newPerson.getId()).getBody();

                    if (customerBoundResponse == null || customerBoundResponse.getStatus() == -2) {
                        return new UserBoundResponse("generateNewCustomer", "server error", -2);
                    }

                    if (customerBoundResponse.getStatus() == -1) {
                        return new UserBoundResponse("generateNewCustomer", "fallback error", -1);
                    }

                    newPerson.setCustomerId(customerBoundResponse.getCustomerOutput().getId());
                } else {
                    /*
                    Driver newDriver = new Driver();
                    newDriver.setLicense("000-123");
                    newDriver.setPerson(newPerson);
                    newPerson.setDriver(newDriver);

                    //Qualification
                    Qualification newQualication = new Qualification();
                    newQualication.setDriver(newDriver);

                    //ServiceRequest
                    ServiceRequest newServiceRequest = new ServiceRequest();
                    newServiceRequest.setDriver(newDriver);

                    qualificationRepository.save(newQualication);
                    serviceRequestRepository.save(newServiceRequest);
                    */


                    //driverRepository.save(newDriver);

                    Driver newDriver = driverClient.generateNewDriver(newPerson.getId()).getBody();
                    newPerson.setDriverId(newDriver.getId());
                    newPerson.setDriver(newDriver);
                }
                personRepository.save(newPerson);

                response = new UserBoundResponse("registerComplete", "success", 1);
                response.setUserOutput(modelMapper.map(user, UserOutput.class));
                return response;
            }
            //NestedFactory nestedFactory = new NestedFactory();
            //User user = (User)(nestedFactory.create(signUp.getDiscriminator(), signUp));
        } catch (Exception e) {
            /*
            AuthResponse response = new AuthResponse();
            response.setMessage("Ocurrio un error en methodo "+Thread.currentThread().getStackTrace()+" : "+e.getMessage());
            response.setStatus(-2);
            return response;
            */

            return new UserBoundResponse("registerComplete", e.getMessage(), -2);
        }
    }

    @Override
    public UserBoundResponse login(String email, String password) {

        try {
            UserBoundResponse response = new UserBoundResponse();

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (!optionalUser.isPresent()) {
                return new UserBoundResponse("login", "not found", 0);
            }

            User user = optionalUser.get();
            Person person = user.getPerson();
            //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            //SecurityContextHolder.getContext().setAuthentication(authentication);

            AuthenticatedOutput authenticatedOutput = new AuthenticatedOutput(user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getPersonType());

            if (person.getPersonType() == 1) {
                //authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
                CustomerBoundResponse customerBoundResponse = customerClient.findCustomerById(person.getCustomerId()).getBody();

                if (customerBoundResponse == null || customerBoundResponse.getStatus() == -2) {
                    return new UserBoundResponse("login", "server error", -2);
                }

                if (customerBoundResponse.getStatus() == -1) {
                    return new UserBoundResponse("login", "fallback error", -1);
                }

                authenticatedOutput.setRoleId(customerBoundResponse.getCustomerOutput().getId());
            }
            if (person.getPersonType() == 2) {
                //authenticatedOutput.setRoleId(getPerson.getDriver().getId());
                Driver getDriver = driverClient.getDriverById(person.getDriverId()).getBody();
                authenticatedOutput.setRoleId(getDriver.getId());
            }

            //String jwt = jwtProvider.generateJwtToken(authentication);
            //authenticatedOutput.setJwt(jwt);
            //authenticatedOutput.setRefreshToken(jwtProvider.generateRefreshToken(getUser, jwt, refreshTokenService));


            String secretKey = "mySecretKey";
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");

            String token = Jwts
                    .builder()
                    .setId("softtekJWT")
                    .setSubject(email)
                    .claim("authorities",
                            grantedAuthorities.stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 600000))
                    .signWith(SignatureAlgorithm.HS512,
                            secretKey.getBytes()).compact();

            String r = "Bearer " + token;
            authenticatedOutput.setToken(r);
            response = new UserBoundResponse("login", "success", 1);
            response.setAuthenticatedOutput(authenticatedOutput);
            return response;
        } catch (Exception e) {
            return new UserBoundResponse("login", "An error ocurred " + e.getMessage(), -2);
        }

    }

    @Override
    public UserBoundResponse loginFixed(String email, String password) {

        try {

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (!optionalUser.isPresent()) {
                return new UserBoundResponse("login", "not found", 0);
            }

            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                Person person = user.getPerson();

                AuthenticatedOutput authenticatedOutput = new AuthenticatedOutput(
                        user.getEmail());
                int roleId = 0;
                if (person.getPersonType() == 1) {
                    //authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
                    CustomerBoundResponse customerBoundResponse = customerClient.findCustomerById(person.getCustomerId()).getBody();

                    if (customerBoundResponse == null || customerBoundResponse.getStatus() == -2) {
                        return new UserBoundResponse("login", "server error", -2);
                    }
                    if (customerBoundResponse.getStatus() == -1) {
                        return new UserBoundResponse("login", "fallback error", -1);
                    }

                    authenticatedOutput.setRoleId(customerBoundResponse.getCustomerOutput().getId());
                }
                if (person.getPersonType() == 2) {
                    //authenticatedOutput.setRoleId(getPerson.getDriver().getId());
                    Driver getDriver = driverClient.getDriverById(person.getDriverId()).getBody();
                    roleId = getDriver.getId();
                }

                String secretKey = "mySecretKey";
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_USER");

                String token = Jwts.builder().setId("softtekJWT").setSubject(authenticatedOutput.getEmail())
                        .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .claim("id", person.getId())
                        .claim("email", user.getEmail())
                        .claim("firstName", person.getFirstName())
                        .claim("lastName", person.getLastName())
                        .claim("role", person.getPersonType())
                        .claim("roleId", roleId)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512,
                                secretKey.getBytes()).compact();

                String r = "Bearer " + token;
                authenticatedOutput.setToken(r);

                UserBoundResponse response = new UserBoundResponse("loginFixed", "success", 1);
                response.setAuthenticatedOutput(authenticatedOutput);
                return response;
            } else {
                return new UserBoundResponse("loginFixed", "Correo o contraseña incorrectos", 0);
            }

        } catch (Exception e) {
            return new UserBoundResponse("loginFixed", "An error ocurred : " + e.getMessage(), -2);

        }

    }

    /*
    @Override
    public AuthResponse refresh(RefreshInput refreshInput) throws Exception {
        RefreshToken refreshToken = refreshTokenService.findById(refreshInput.getRefreshToken()).get();
        String tokenId = jwtProvider.getJwtTokenId(refreshInput.getToken());

        if (refreshToken.getToken().equals(tokenId) && jwtProvider.validateRefreshToken(refreshToken)) {
            User user = refreshToken.getUser();
            refreshToken.setState(false);
            refreshTokenService.save(refreshToken);
            return login(user.getEmail(), user.getPassword());
        }
        return new AuthResponse("Can't validate token");
    }
    */

}