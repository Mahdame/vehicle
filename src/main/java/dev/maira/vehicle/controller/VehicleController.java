package dev.maira.vehicle.controller;

import dev.maira.vehicle.entity.Vehicle;
import dev.maira.vehicle.exception.EntityNotFoundException;
import dev.maira.vehicle.mapper.MapperDtoEntity;
import dev.maira.vehicle.openapi.api.VehiclesApi;
import dev.maira.vehicle.openapi.model.*;
import dev.maira.vehicle.service.IVehicleService;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static dev.maira.vehicle.util.Constants.TRACE_ID;
import static dev.maira.vehicle.util.Constants.X_TRACE_ID;
import static java.util.Objects.nonNull;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {
    private final IVehicleService service;
    private final MapperDtoEntity mapper;

    @Override
    public ResponseEntity<ListVehiclesResponseDto> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        ListVehiclesResponseDto responseDto = mapper.convertVehicleCollectionToListDTO(service.findAll());

        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @Override
    public ResponseEntity<GetVehicleResponseDto> getVehicle(String vehicleId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle persistedEntity = service.findById(UUID.fromString(vehicleId))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Vehicle not found.", vehicleId)));

        return ResponseEntity.ok().headers(headers).body(mapper.mapEntityToDto(persistedEntity));
    }

    @Override
    public ResponseEntity<PostVehicleResponseDto> postVehicle(PostVehicleRequestDto postVehicleRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));
        
        Vehicle vehicle = mapper.mapDtoToPostEntity(postVehicleRequestDto);

        vehicle = service.save(vehicle);
        return new ResponseEntity<>(mapper.mapEntityToPostResponseDto(vehicle), headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> putVehicle(String vehicleId, PutVehicleRequestDto putVehicleRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle persistedVehicle = service.findById(UUID.fromString(vehicleId))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Vehicle not found.", vehicleId)));

        Vehicle vehicle = mapper.mapDtoToPutEntity(putVehicleRequestDto);

        persistedVehicle.setCustomerOwner(vehicle.getCustomerOwner());
        persistedVehicle.setTelemetryProfile(vehicle.getTelemetryProfile());
        persistedVehicle.setCurrentDriver(vehicle.getCurrentDriver());
        persistedVehicle.setNumberPlate(vehicle.getNumberPlate());
        persistedVehicle.setVin(vehicle.getVin());
        persistedVehicle.setColor(vehicle.getColor());

        service.update(persistedVehicle);
        return ResponseEntity.noContent().headers(headers).build();
    }

    @Override
    public ResponseEntity<Void> deleteVehicle(String vehicleId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

        Vehicle vehicle = service.findById(UUID.fromString(vehicleId))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Vehicle not found.", vehicleId)));

        service.delete(vehicle);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
