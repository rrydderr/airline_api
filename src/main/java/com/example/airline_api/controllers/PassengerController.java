package com.example.airline_api.controllers;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    // Display details of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    // Display specific passenger details
    @GetMapping(value = "/{id}")
    public ResponseEntity<Passenger> getPassengerById(Long id) {
        return new ResponseEntity<>(passengerService.findPassengerById(id), HttpStatus.OK);
    }

    // Add a new passenger
    @PatchMapping
    public ResponseEntity<Passenger> addNewPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
        return new ResponseEntity<>(passenger, HttpStatus.CREATED);
    }

}
