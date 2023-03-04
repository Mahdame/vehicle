package dev.maira.vehicle.repository;

import dev.maira.vehicle.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends IBaseRepository<Vehicle, UUID> {
}
