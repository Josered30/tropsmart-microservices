package com.softper.driverservice.client;

import com.softper.driverservice.resources.comunications.CargoBoundResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("cargo-service")
@RequestMapping("/cargoservice/cargoes")
public interface CargoClient {

    @GetMapping("/{cargoId}")
    public ResponseEntity<CargoBoundResponse> findCargoById(@PathVariable(value = "cargoId")int cargoId);
    
}
