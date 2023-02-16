package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).get();
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    //add passenger to flight
    public Flight addPassengerToFlight(Long passengerId, Long flightId) {
        Flight flight = flightRepository.findById(flightId).get();
        Passenger passenger = passengerRepository.findById(passengerId).get();
        if (flight != null && passenger != null) {
            {
                List<Passenger> passengers = flight.getPassengers();
                passengers.add(passenger);
                flight.setPassengers(passengers);
            }
            return flightRepository.save(flight);

        } else {
            return null;
        }
    }


    //delete flight
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            List<Passenger> passengers = flight.getPassengers();
            for (Passenger passenger : passengers) {
                passenger.getFlights().remove(flight);
            }
            flightRepository.delete(flight);
        }


    }
}
