package com.softper.cargoservice.servicesImp;

import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
//import com.softper.cargoservice.resources.comunications.CargoResponseFixed;
import com.softper.cargoservice.resources.inputs.CargoInput;
import com.softper.cargoservice.resources.outputs.CargoOutput;
import com.softper.cargoservice.resources.outputs.CargoOutputFixed;
import com.softper.cargoservice.services.ICargoService;
import com.softper.cargoservice.exception.ResourceNotFoundException;
import com.softper.cargoservice.models.*;
import com.softper.cargoservice.repositories.*;
//import com.softper.cargoservice.resources.comunications.CargoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService implements ICargoService {

    //@Autowired
    //private ICargoRepository cargoRepository;

    //@Autowired
    //private IUserRepository userRepository;
    //@Autowired
    //private ICustomerRepository customerRepository;
    //@Autowired
    //private IPriceRepository priceRepository;
    //@Autowired
    //private IBalanceRepository balanceRepository;
    //@Autowired
    //private IServiceRepository serviceRepository;
    //@Autowired
    //private IServiceRequestRepository serviceRequestRepository; 
    //@Autowired
    //private ILocationRepository locationRepository;




    @Override
    public Cargo save(Cargo cargo) throws Exception {
        //return cargoRepository.save(cargo);
        return null;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //cargoRepository.deleteById(id);
    }

    @Override
    public Optional<Cargo> findById(Integer id) throws Exception {
        //return cargoRepository.findById(id);
        return null;
    }

    @Override
    public List<Cargo> findAll() throws Exception {
        //return cargoRepository.findAll();
        return null;
    }


    @Override
    public CargoBoundResponse findCargoesByCustomerId(int customerId) {
        /*try
        {
            List<Cargo> cargoes = cargoRepository.findCargoesByCustomerId(customerId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting cargo list: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CargoBoundResponse addCargoByCustomerId(int customerId, CargoInput cargoInput) {
        /*try
        {
            com.softper.userservice.models.Service getService = serviceRepository.findById(cargoInput.getServiceId()).get();
            Customer getCustomer = customerRepository.findById(customerId)
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));
            User getUser = userRepository.findUserByPersonId(getCustomer.getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));

            Balance getBalance = getUser.getBalance();
            if(getCustomer.getCredits() - cargoInput.getServicePrice()<0)
                return new CargoResponse("You dont have enough credits");
            Price newPrice = new Price();
            newPrice.setTotalPrice((double) cargoInput.getServicePrice());
            newPrice.setTax(((double) cargoInput.getServicePrice()) * 0.19);
            newPrice.setPriceType(2);
            newPrice = priceRepository.save(newPrice);

            getBalance.setSpentMoney(getBalance.getSpentMoney() + newPrice.getTotalPrice());
            getCustomer.setCredits(getCustomer.getCredits() - newPrice.getTotalPrice());
            getBalance = balanceRepository.save(getBalance);
            getCustomer = customerRepository.save(getCustomer);

            Cargo newCargo = new Cargo();
            newCargo.setCustomer(getCustomer);
            newCargo.setService(getService);
            newCargo.setDescription(cargoInput.getDescription());
            newCargo.setWeight(cargoInput.getWeight());
            newCargo.setPrice(newPrice);
            newCargo.setCargoType("Chemicals");
            newCargo.setCargoStatus("Awaiting");
            newCargo = cargoRepository.save(newCargo);

            Location newLocation = new Location();
            newLocation.setArrivalAltitude(1.0);
            newLocation.setArrivalLatitude(2.0);
            newLocation.setArrivalLongitude(3.0);
            newLocation.setDepartureAltitude(4.0);
            newLocation.setDepartureLatitude(5.0);
            newLocation.setDepartureLongitude(6.0);
            newLocation.setRoute(getService.getRoute());
            newLocation.setCargo(newCargo);
            newLocation = locationRepository.save(newLocation);

            return new CargoResponse(toCargoOutput(newCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while registering a cargo: "+e.getMessage());
        }¨
        */
        return null;

    }

    @Override
    public CargoBoundResponse findCargoById(int cargoId) {
        /*try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting a cargo: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CargoBoundResponse findAllCargoes() {
        /*try
        {
            List<Cargo> cargoes = cargoRepository.findAll();
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CargoBoundResponse findAllCargoesFixed() {
        /*try
        {
            List<Cargo> cargoes = cargoRepository.findAll();
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CargoBoundResponse setCargoDelivered(int cargoId) {
        /*try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();

            getCargo.setCargoStatus("Done");
            getCargo = cargoRepository.save(getCargo);

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }*/
        return null;
    }


    @Override
    public CargoBoundResponse findCargoesByDriverId(int driverId) {
        /*try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.userservice.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    cargoOutputList.add(toCargoOutput(c));
                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CargoBoundResponse findRequestedCargoesByDriverId(int driverId) {
        /*try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.userservice.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("Awaiting"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }

                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
        */
        return null;
    }

    @Override
    public CargoBoundResponse findConfirmedCargoesByDriverId(int driverId) {
        /*try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.userservice.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("In process"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
        */
        return null;
    }

    @Override
    public CargoBoundResponse findFinishedCargoesByDriverId(int driverId) {
        /*try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.softper.userservice.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("Done"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
        */
        return null;
    }



    @Override
    public CargoBoundResponse confirmCargoRequest(int cargoId) {
        /*try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();
            getCargo.setCargoStatus("In process");
            getCargo = cargoRepository.save(getCargo);

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }*/
        return null;
    }

    @Override
    public CargoBoundResponse rejectCargoById(int cargoId) {
        /*try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();
            getCargo.setCargoStatus("Rejected");
            getCargo = cargoRepository.save(getCargo);

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }
        */
        return null;
    }


    public CargoOutput toCargoOutput(Cargo cargo){
        /*
        CargoOutput newCargoOutput = new CargoOutput();
        newCargoOutput.setId(cargo.getId());
        newCargoOutput.setWeight(cargo.getWeight());
        newCargoOutput.setCustomer(cargo.getCustomer().getPerson().getFirstName()+" "+cargo.getCustomer().getPerson().getFirstName());
        newCargoOutput.setDriver(cargo.getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+cargo.getService().getServicesRequest().getDriver().getPerson().getLastName());
        newCargoOutput.setStartTime(cargo.getService().getStartTime());
        newCargoOutput.setFinishTime(cargo.getService().getFinishTime());
        newCargoOutput.setServicePrice(cargo.getPrice().getTotalPrice());
        newCargoOutput.setDescription(cargo.getDescription());
        newCargoOutput.setCargoType(cargo.getCargoType().toString());
        newCargoOutput.setCargoStatus(cargo.getCargoStatus());

        return newCargoOutput;
        */
        return null;
    }




}
