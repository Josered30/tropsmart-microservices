package com.softper.configservice;

import java.util.Optional;

import com.softper.configservice.models.*;
import com.softper.configservice.repositories.IConfigurationRepository;
import com.softper.configservice.servicesImp.ConfigurationService;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigserviceUnitTests {
    
    @Mock
    private IConfigurationRepository configurationRepository;

    private ConfigurationService configurationServiceImpl;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        configurationServiceImpl = new ConfigurationService();
        Configuration configuration = new Configuration();
        configuration.setId(1);
        configuration.setLanguage("English");
        configuration.setPaymentCurrency("DOLARS");

        Mockito.when(configurationRepository.findById(1))
            .thenReturn(Optional.of(configuration));
    }

    @Test
    public void whenValidGetId_ThenReturnConfiguration() throws Exception {
        Configuration configuration = new Configuration();
        configuration.setId(1);
        configuration.setLanguage("English");
        configuration.setPaymentCurrency("DOLARS");

        Assertions.assertThat(configuration.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetId_ThenReturnPaymentMethod() throws Exception {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1);
        paymentMethod.setBankName("Interbank");
        paymentMethod.setBillingAdress("2874-2323");

        Assertions.assertThat(paymentMethod.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetId_ThenReturnReport() throws Exception {
        Report report = new Report();
        report.setId(1);
        report.setDescription("Report test");

        Assertions.assertThat(report.getId()).isEqualTo(1);
    }
}   
