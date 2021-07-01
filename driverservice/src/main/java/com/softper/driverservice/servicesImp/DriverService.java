package com.softper.driverservice.servicesImp;

import com.softper.driverservice.repositories.IDriverRepository;
import com.softper.driverservice.repositories.IQualificationRepository;
import com.softper.driverservice.repositories.IServiceRequestRepository;
import com.softper.driverservice.services.IDriverService;
import com.softper.driverservice.models.Driver;
import com.softper.driverservice.models.Location;
import com.softper.driverservice.models.Qualification;
import com.softper.driverservice.models.ServiceRequest;


import com.tropsmart.resources.comunications.DriverBoundResponse;
import com.tropsmart.resources.outputs.DriverOutput;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

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
        try {
            Optional<Driver> getDriver = driverRepository.findById(driverId);
            DriverBoundResponse response = new DriverBoundResponse("findDriverById", "success", 1);
            getDriver.ifPresent(driver -> response.setDriverOutput(modelMapper.map(driver, DriverOutput.class)));
            return response;

        } catch (Exception e) {
            return new DriverBoundResponse("findDriverById", "An error ocurred : " + e.getMessage(), -2);
        }
    }


    @Override
    public DriverBoundResponse findAllDrivers() {
        try {
            List<Driver> drivers = driverRepository.findAll();
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (Driver driver : drivers) {
                driverOutputList.add(modelMapper.map(driver, DriverOutput.class));
            }
            DriverBoundResponse response = new DriverBoundResponse("findAllDrivers", "success", 1);
            response.setDriverOutputs(driverOutputList);
            return response;
        } catch (Exception e) {
            return new DriverBoundResponse("findAllDrivers", "An error ocurred : " + e.getMessage(), -2);
        }
    }

    @Override
    public DriverBoundResponse generateNewDriver(int personId) {
        try {
            Driver newDriver = new Driver();
            newDriver.setLicense("000-123");
            newDriver.setPersonId(personId);

            Qualification newQualification = new Qualification();
            newQualification.setDriver(newDriver);

            ServiceRequest newServiceRequest = new ServiceRequest();
            newServiceRequest.setDriver(newDriver);

            qualificationRepository.save(newQualification);
            serviceRequestRepository.save(newServiceRequest);

            DriverBoundResponse response = new DriverBoundResponse("generateNewDriver", "success", 1);
            response.setDriverOutput(modelMapper.map(newDriver, DriverOutput.class));

            return response;
        } catch (Exception e) {
            return new DriverBoundResponse("generateNewDriver", "Hubo un error en el metodo : " + e.getMessage(), -2);
        }
    }

    @Override
    public DriverBoundResponse findDriverByPersonId(int personId) {
        try {
            Optional<Driver> optionalDriver = driverRepository.findDriverByPersonId(personId);
            DriverBoundResponse response = new DriverBoundResponse("findDriverByPersonId", "success", 1);
            optionalDriver.ifPresent(driver -> response.setDriverOutput(modelMapper.map(driver, DriverOutput.class)));
            return response;
        } catch (Exception e) {
            return null;
        }
    }


}
