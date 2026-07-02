package org.internship.service;

import org.internship.entity.Vehicle;
import org.internship.repository.VehicleRepository;
import java.util.List;

public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void registerVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        System.out.println("Vehicle registered: " + vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}