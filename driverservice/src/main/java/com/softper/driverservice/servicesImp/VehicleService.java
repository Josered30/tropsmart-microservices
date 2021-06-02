package com.softper.driverservice.servicesImp;

import com.softper.driverservice.repositories.IDriverRepository;
import com.softper.driverservice.repositories.IQualificationRepository;
import com.softper.driverservice.repositories.IServiceRequestRepository;
import com.softper.driverservice.repositories.ISoatRepository;
import com.softper.driverservice.repositories.IVehicleRepository;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.resources.inputs.VehicleInput;
import com.softper.driverservice.resources.outputs.DriverOutput;
import com.softper.driverservice.resources.outputs.PersonOutput;
import com.softper.driverservice.resources.outputs.VehicleOutput;
import com.softper.driverservice.services.IVehicleService;
import com.softper.driverservice.client.UserClient;
import com.softper.driverservice.exception.ResourceNotFoundException;
import com.softper.driverservice.models.Driver;
import com.softper.driverservice.models.Qualification;
import com.softper.driverservice.models.ServiceRequest;
import com.softper.driverservice.models.Soat;
import com.softper.driverservice.models.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private ISoatRepository soatRepository;

    @Autowired
    private UserClient userClient;

    @Transactional
    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Optional<Vehicle> findById(Integer id) throws Exception {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAll() throws Exception {
        return vehicleRepository.findAll();
    }


    @Override
    public DriverBoundResponse findVehiclesByDriverId(int driverId) {
        try
        {
            List<Vehicle> vehicles = vehicleRepository.getVehiclesByDriverId(driverId);
            List<VehicleOutput> vehicleOutputList = new ArrayList<>();
            for (Vehicle getVehicle:vehicles) {
                PersonOutput getPerson = userClient.findPersonById(getVehicle.getDriver().getId()).getBody().getPersonOutput();
                vehicleOutputList.add(toVehicleOutput(getVehicle, getPerson));
            }
            DriverBoundResponse response = new DriverBoundResponse("findVehiclesByDriverId", "success",1);
            response.setVehicleOutputs(vehicleOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findVehiclesByDriverId", "An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public DriverBoundResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput) {
        try
        {
            Driver getDriver = driverRepository.findById(driverId)
                    .orElseThrow(()-> new ResourceNotFoundException("driver","id",driverId));

            PersonOutput getPerson = userClient.findPersonById(getDriver.getPersonId()).getBody().getPersonOutput();

            Soat newSoat = new Soat();
            newSoat.setAssociateName(getPerson.getFirstName()+" "+getPerson.getLastName());
            newSoat.setEmissionDate(Calendar.getInstance().getTime());
            newSoat.setExpireDate(Calendar.getInstance().getTime());
            newSoat.setServiceType("Servicio de carga");

            newSoat = soatRepository.save(newSoat);

            Vehicle newVehicle = new Vehicle();
            newVehicle.setBrand(vehicleInput.getBrand());
            newVehicle.setLoadingCapacity(vehicleInput.getLoadingCapacity());
            newVehicle.setModel(vehicleInput.getModel());
            newVehicle.setFabricationYear(Calendar.getInstance().getTime());
            newVehicle.setOwnershipCard(vehicleInput.getOwnershipCard());

            newVehicle.setDriver(getDriver);
            newVehicle.setSoat(newSoat);

            newVehicle = vehicleRepository.save(newVehicle);

            DriverBoundResponse response = new DriverBoundResponse("addVehicleByUserId","success",1);
            response.setVehicleOutput(toVehicleOutput(newVehicle, getPerson));;
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("addVehicleByUserId", "An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public DriverBoundResponse findAllVehicles() {
        try
        {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            List<VehicleOutput> vehicleOutputList = new ArrayList<>();
            for (Vehicle getVehicle:vehicles) {
                PersonOutput getPerson = userClient.findPersonById(getVehicle.getDriver().getId()).getBody().getPersonOutput();
                vehicleOutputList.add(toVehicleOutput(getVehicle, getPerson));
            }
            DriverBoundResponse response = new DriverBoundResponse("findAllVehicles", "success",1);
            response.setVehicleOutputs(vehicleOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findAllVehicles", "An error ocurred : "+e.getMessage(),-2);
        }
    }

    @Override
    public DriverBoundResponse findVehicleById(int vehicleId) {
        try
        {
            Optional<Vehicle> getVehicle = vehicleRepository.findById(vehicleId);
            DriverBoundResponse response = new DriverBoundResponse("findVehicleById","success",1);
            if(getVehicle.isPresent()){
                PersonOutput getPerson = userClient.findPersonById(getVehicle.get().getDriver().getPersonId()).getBody().getPersonOutput();
                response.setVehicleOutput(toVehicleOutput(getVehicle.get(), getPerson));
            }
            return response;
        }
        catch (Exception e)
        {
            return new DriverBoundResponse("findVehicleById","An error ocurred : "+e.getMessage(),-2);
        }
    }

    public VehicleOutput toVehicleOutput(Vehicle getVehicle, PersonOutput getPerson) {
        VehicleOutput newVehicleOutput = new VehicleOutput();
        newVehicleOutput.setDriver(getPerson.getFirstName()+" "+getPerson.getLastName());
        newVehicleOutput.setModel(getVehicle.getModel());
        newVehicleOutput.setBrand(getVehicle.getBrand());
        newVehicleOutput.setLoadingCapacity(getVehicle.getLoadingCapacity());
        return newVehicleOutput;
    }

   
}
