package com.softper.driverservice;

import java.util.Optional;

import com.softper.driverservice.models.Driver;
import com.softper.driverservice.models.Review;
import com.softper.driverservice.models.Vehicle;
import com.softper.driverservice.repositories.IDriverRepository;
import com.softper.driverservice.services.IDriverService;
import com.softper.driverservice.servicesImp.DriverService;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriverserviceUnitTests {
    
    @Mock
    private IDriverRepository driverRepository;

    private IDriverService driverService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        driverService = new DriverService();
        Driver driver = new Driver();
        driver.setId(1);
        driver.setLicense("1234-2323");
        
        Mockito.when(driverRepository.findById(1))
            .thenReturn(Optional.of(driver));
    }

    @Test
    public void whenValidGetFirstNameLastName_ThenReturnDriver() throws Exception {
        Driver driver = new Driver();
        driver.setId(1);
        driver.setLicense("1234-2323");

        Assertions.assertThat(driver.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetFirstNameLastName_ThenReturnReview() throws Exception {
        Review review = new Review();
        review.setId(1);
        review.setCommentary("Commentary test");
        review.setCalification(4.5);
        Assertions.assertThat(review.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetFirstNameLastName_ThenReturnVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setLoadingCapacity(750.6);
        vehicle.setModel("Test");
        vehicle.setBrand("Mercedes-benz");
        Assertions.assertThat(vehicle.getId()).isEqualTo(1);
    }
}
