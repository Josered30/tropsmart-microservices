package com.softper.userservice.servicesImp;

import com.google.common.base.Strings;
import com.softper.userservice.resources.comunications.ConfigBoundResponse;
import com.softper.userservice.resources.comunications.CustomerBoundResponse;
import com.softper.userservice.resources.comunications.DriverBoundResponse;

//import com.softper.userservice.repositories.IConfigurationRepository;
import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.client.ConfigurationClient;
import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.client.DriverClient;
//import com.softper.userservice.repositories.IDriverRepository;
//import com.softper.userservice.repositories.IQualificationRepository;
import com.softper.userservice.exception.ResourceNotFoundException;
import com.softper.userservice.models.*;
import com.softper.userservice.repositories.*;
import com.softper.userservice.resources.inputs.RefreshInput;
import com.softper.userservice.resources.inputs.SignUp;
import com.softper.userservice.resources.outputs.AuthenticatedOutput;
import com.softper.userservice.resources.outputs.UserOutput;
import com.softper.userservice.security.JwtProvider;
import com.softper.userservice.services.IAuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.validator.cfg.GenericConstraintDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private IBalanceRepository balanceRepository;
    
    @Autowired
    private JwtProvider jwtProvider;

    //@Autowired
    //private ICustomerRepository customerRepository;
    @Autowired
    private CustomerClient customerClient;

    //@Autowired
    //private IDriverRepository driverRepository;
    @Autowired
    private DriverClient driverClient;

    //@Autowired
    //private IQualificationRepository qualificationRepository;

    //@Autowired
    //private IServiceRequestRepository serviceRequestRepository;

    //@Autowired
    //private IConfigurationRepository configurationRepository;
    @Autowired
    private ConfigurationClient configurationClient;


    //@Autowired
    //private AuthenticationManager authenticationManager;


    //@Autowired
    //private IRefreshTokenService refreshTokenService;

    //@Autowired
    //private PasswordEncoder encoder;



    @Override
    public UserBoundResponse registerComplete(SignUp signUp) {
        
        try
        {
            UserBoundResponse response = new UserBoundResponse();
            Optional<User> result = userRepository.findByEmail(signUp.getEmail());
            if(result.isPresent()) {
                return new UserBoundResponse("registerComplete", "El correo : "+result.get().getEmail()+" ya se encuentra registrado",0);
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


                Balance newBalance = new Balance();
                newBalance.setSpentMoney(0);
                newBalance.setAddedMoney(0);
    
                newBalance = balanceRepository.save(newBalance);
    
    
                User user = new User();
                user.setEmail(signUp.getEmail());
                user.setPerson(newPerson);
                
    
                //user.setPassword(encoder.encode((signUp.getPassword())));
                user.setPassword(signUp.getPassword());
                user.setCreatedAt(Calendar.getInstance().getTime());
                //user.setConfiguration(newConfiguration);
                user.setConfigurationId(configurationResponse.getConfigurationOutput().getId());
                user.setBalance(newBalance);
    
                user = userRepository.save(user);
    
    
                if(signUp.getDiscriminator() == 1) {
                    //Customer newCustomer = new Customer();
                    //newCustomer.setCredits(0.0);
                    //newCustomer.setPerson(newPerson);

                    //newPerson.setCustomer(newCustomer);
                    //customerRepository.save(newCustomer);
                    Customer newCustomer = customerClient.generateNewCustomer(newPerson.getId()).getBody();
                    newPerson.setCustomerId(newCustomer.getId());
                    newPerson.setCustomer(newCustomer);
                }
                else
                {
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
                newPerson = personRepository.save(newPerson);

                //response.setResource(new AuthenticatedOutput(user.getId(),user.getEmail(),user.getPassword(),signUp.getFirstName(),signUp.getLastName(),signUp.getDiscriminator()));
                //response.setStatus(1);
                response = new UserBoundResponse("registerComplete","success",1);
                response.setUserOutput(toUserOutput(user));
                return response;
            }
            //NestedFactory nestedFactory = new NestedFactory();
            //User user = (User)(nestedFactory.create(signUp.getDiscriminator(), signUp));
           }
        catch (Exception e)
        {
            /*
            AuthResponse response = new AuthResponse();
            response.setMessage("Ocurrio un error en methodo "+Thread.currentThread().getStackTrace()+" : "+e.getMessage());
            response.setStatus(-2);
            return response;
            */

            return new UserBoundResponse("registerComplete",e.getMessage(),-2);
        }        
    }

    @Override
    public UserBoundResponse login(String email, String password) {
        
        try {
            UserBoundResponse response = new UserBoundResponse();
            User getUser = userRepository.findByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("user","email",email));
            Person getPerson = getUser.getPerson();


            //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            //SecurityContextHolder.getContext().setAuthentication(authentication);

            AuthenticatedOutput authenticatedOutput =new AuthenticatedOutput(getUser.getId(),
                    getUser.getEmail(),
                    getUser.getPassword(),
                    getPerson.getFirstName(),
                    getPerson.getLastName(),
                    getPerson.getPersonType());

            if(getPerson.getPersonType()==1){
                //authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
                Customer getCustomer = customerClient.getCustomerById(getPerson.getCustomerId()).getBody();
                authenticatedOutput.setRoleId(getCustomer.getId());
                
            }
            if(getPerson.getPersonType()==2){
                //authenticatedOutput.setRoleId(getPerson.getDriver().getId());
                Driver getDriver = driverClient.getDriverById(getPerson.getDriverId()).getBody();
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

            String r = "Bearer "+token;
            authenticatedOutput.setToken(r);
            response = new UserBoundResponse("login","success",1);
            response.setAuthenticatedOutput(authenticatedOutput);
            return response;
        }
        catch (Exception e)
        {
            return new UserBoundResponse("login","An error ocurred "+e.getMessage(),-2);
        }
        
    }

    @Override
    public UserBoundResponse loginFixed(String email, String password) {
        
        try {
            UserBoundResponse response = new UserBoundResponse();
            User getUser = userRepository.findByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("user","email",email));
            if(getUser.getPassword().equals(password)){
                Person getPerson = getUser.getPerson();
                AuthenticatedOutput authenticatedOutput =new AuthenticatedOutput(
                        getUser.getEmail());
                int roleId=0;
                if(getPerson.getPersonType()==1){
                    //authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
                    Customer getCustomer = customerClient.getCustomerById(getPerson.getCustomerId()).getBody();
                    roleId = getCustomer.getId();
                    
                }
                if(getPerson.getPersonType()==2){
                    //authenticatedOutput.setRoleId(getPerson.getDriver().getId());
                    Driver getDriver = driverClient.getDriverById(getPerson.getDriverId()).getBody();
                    roleId = getDriver.getId();
                }

                String secretKey = "mySecretKey";
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_USER");

                String token = Jwts.builder().setId("softtekJWT").setSubject(authenticatedOutput.getEmail())
                        .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .claim("id", getUser.getId())
                        .claim("email", getUser.getEmail())
                        .claim("firstName", getPerson.getFirstName())
                        .claim("lastName", getPerson.getLastName())
                        .claim("role", getPerson.getPersonType())
                        .claim("roleId", roleId)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512,
                                secretKey.getBytes()).compact();

                String r = "Bearer "+token;
                authenticatedOutput.setToken(r);

                response = new UserBoundResponse("loginFixed","success",1);
                response.setAuthenticatedOutput(authenticatedOutput);
                return response;
            }
            else {
                return new UserBoundResponse("loginFixed","Correo o contraseña incorrectos",0);
            }
            
        }
        catch (Exception e)
        {
            return new UserBoundResponse("loginFixed","An error ocurred : "+e.getMessage(),-2);

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

    public UserOutput toUserOutput(User getUser)
    {
        UserOutput newUserOutput = new UserOutput();
        newUserOutput.setEmail(getUser.getEmail());
        newUserOutput.setFirstName(getUser.getPerson().getFirstName());
        newUserOutput.setLastName(getUser.getPerson().getLastName());
        newUserOutput.setId(getUser.getId());
        if(getUser.getPerson().getCustomer()!=null)
        {
            newUserOutput.setRole("1");
            newUserOutput.setRoleId(getUser.getPerson().getCustomer().getId());
        } else {
            newUserOutput.setRole("2");
            newUserOutput.setRoleId(getUser.getPerson().getDriver().getId());
        }

        return newUserOutput;
    }
}