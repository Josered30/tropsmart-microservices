package com.softper.driverservice.servicesImp;

import com.softper.driverservice.repositories.IDriverRepository;
import com.softper.driverservice.repositories.IQualificationRepository;
import com.softper.driverservice.repositories.IServiceRequestRepository;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.resources.outputs.DriverOutput;
import com.softper.driverservice.services.IDriverService;
import com.softper.driverservice.exception.ResourceNotFoundException;
import com.softper.driverservice.models.Driver;
import com.softper.driverservice.models.Location;
import com.softper.driverservice.models.Qualification;
import com.softper.driverservice.models.ServiceRequest;
import com.softper.driverservice.models.User;
//import com.softper.driverservice.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements IDriverService {

    @Autowired
    IDriverRepository driverRepository;

    
    @Autowired 
    private IQualificationRepository qualificationRepository;

    @Autowired
    private IServiceRequestRepository serviceRequestRepository;


    //@Autowired
    //IUserRepository userRepository;


    @Override
    public Driver save(Driver driver) throws Exception {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        driverRepository.deleteById(id);
    }

    @Override
    public Optional<Driver> findById(Integer id) throws Exception {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() throws Exception {
        //return driverRepository.findAll();
        return null;

    }


    @Override
    public DriverBoundResponse findNearDrivers(Location location) {
        return null;
    }

    @Override
    public DriverBoundResponse findDriverById(int driverId) {
        try
        {
            Optional<Driver> getDriver = driverRepository.findById(driverId);
            DriverBoundResponse response = new DriverBoundResponse("findDriverById","success",1);
            if(getDriver.isPresent())
                response.setDriverOutput(toDriverOutput(getDriver.get()));
            
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findDriverById","An error ocurred : "+e.getMessage(),-2);
        }
    }


    @Override
    public DriverBoundResponse findAllDrivers() {
        try
        {
            List<Driver> drivers = driverRepository.findAll();
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (Driver getDriver:drivers) {
                driverOutputList.add(toDriverOutput(getDriver));
            }
            DriverBoundResponse response = new DriverBoundResponse("findAllDrivers","success",1);
            response.setDriverOutputs(driverOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findAllDrivers","An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public Driver generateNewDriver(int personId) {
        try{
            Driver newDriver = new Driver();
            newDriver.setLicense("000-123");
            newDriver.setPersonId(personId);
            
            Qualification newQualification = new Qualification();
            newQualification.setDriver(newDriver);

            ServiceRequest newServiceRequest = new ServiceRequest();
            newServiceRequest.setDriver(newDriver);
            
            qualificationRepository.save(newQualification);
            serviceRequestRepository.save(newServiceRequest);

            //DriverBoundResponse response = new DriverBoundResponse("generateNewDriver","success",1);
            //response.setDriverOutput(toDriverOutput(newDriver));
            return newDriver;
        }catch(Exception e){
            return null;
            //return new DriverBoundResponse("generateNewDriver","Hugo un error en el metodo : "+e.getMessage(),-2);
        }
    }

    @Override
    public Driver findDriverByPersonId(int personId){
        try{
            return driverRepository.findDriverByPersonId(personId);
        } catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public Driver getDriverById(int driverId)
    {   
        try{
            Optional<Driver> getDriver = driverRepository.findById(driverId);
            if(getDriver.isPresent())
                return getDriver.get();
            else
                return null;
        } catch(Exception e)
        {
            return null;
        }
    }


    public DriverOutput toDriverOutput(Driver getDriver) {
        DriverOutput newDriverOutput = new DriverOutput();
        newDriverOutput.setRole(2);
        newDriverOutput.setRoleId(getDriver.getId());
        newDriverOutput.setLicense(getDriver.getLicense());
        return newDriverOutput;
    }
}
