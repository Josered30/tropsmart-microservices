package com.softper.cargoservice;

import java.util.Optional;

import com.softper.cargoservice.models.Cargo;
import com.softper.cargoservice.models.Price;
import com.softper.cargoservice.models.Route;
import com.softper.cargoservice.models.Service;
import com.softper.cargoservice.repositories.ICargoRepository;
import com.softper.cargoservice.servicesImp.CargoService;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CargoserviceUnitTests {
    
    @Mock
    private ICargoRepository cargoRepository;

    private CargoService cargoService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        cargoService = new CargoService();
        Cargo cargo = new Cargo();
        cargo.setId(1);
        cargo.setWeight(120.5);
        cargo.setCargoStatus("Enabled");
        cargo.setDescription("test");

        Mockito.when(cargoRepository.findById(1))
        .thenReturn(Optional.of(cargo));
    }

    @Test
    public void whenValidGetID_ThenReturnCargo() throws Exception {
        Cargo cargo = new Cargo();
        cargo.setId(1);
        cargo.setWeight(120.5);
        cargo.setCargoStatus("Enabled");
        cargo.setDescription("test");

        Assertions.assertThat(cargo.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetID_ThenReturnPrice() throws Exception {
        Price price = new Price();
        price.setId(1);
        price.setTotalPrice(120.5);
        price.setTax(17.5);

        Assertions.assertThat(price.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetID_ThenReturnRoute() throws Exception {
        Route route = new Route();
        route.setCargoUnits(30);
        route.setDistance(3500.0);
        route.setId(1);
        Assertions.assertThat(route.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetID_ThenReturnService() throws Exception {
        Service service = new Service();
        service.setId(1);
        service.setServiceState("Loading");

        Assertions.assertThat(service.getId()).isEqualTo(1);
    }


}
