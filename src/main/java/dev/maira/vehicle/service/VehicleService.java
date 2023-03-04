package dev.maira.vehicle.service;

import dev.maira.vehicle.entity.Vehicle;
import dev.maira.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class VehicleService extends BaseEntityService<UUID, Vehicle, VehicleRepository> implements IVehicleService {

	public VehicleService(VehicleRepository repository) {
		super(repository);
	}
	
	public static Set<Vehicle> convertSetIdsToGetAll(Collection<String> listIds) {

		Set<Vehicle> vehicles = new HashSet<>();

		listIds.forEach(id->{
			Vehicle vehicle = new Vehicle();
			vehicle.setId(UUID.fromString(id));
			vehicles.add(vehicle);
		});

	    return vehicles;
	  }

}
