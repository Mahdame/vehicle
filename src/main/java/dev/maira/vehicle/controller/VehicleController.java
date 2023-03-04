package dev.maira.vehicle.controller;

import dev.maira.vehicle.mapper.MapperDtoEntity;
import dev.maira.vehicle.openapi.api.VehiclesApi;
import dev.maira.vehicle.openapi.model.*;
import dev.maira.vehicle.service.IVehicleService;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static dev.maira.vehicle.util.Constants.TRACE_ID;
import static dev.maira.vehicle.util.Constants.X_TRACE_ID;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {
    private final IVehicleService service;
    private final MapperDtoEntity mapper;

    public ResponseEntity<PostVehicleResponseDto> postVehicle(PostVehicleRequestDto postVehicleRequestDto) {
        return VehiclesApi.super.postVehicle(postVehicleRequestDto);
    }

    @Override
    public ResponseEntity<Void> deleteVehicle(String vehicleId) {
        return VehiclesApi.super.deleteVehicle(vehicleId);
    }

    @Override
    public ResponseEntity<GetVehicleResponseDto> getVehicle(String vehicleId) {
        return VehiclesApi.super.getVehicle(vehicleId);
    }

    @Override
    public ResponseEntity<ListVehiclesResponseDto> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_TRACE_ID, MDC.get(TRACE_ID));
        ListVehiclesResponseDto responseDto = mapper.convertVehicleCollectionToListDTO(service.findAll());
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @Override
    public ResponseEntity<Void> putVehicle(String vehicleId, PutVehicleRequestDto putVehicleRequestDto) {
        return VehiclesApi.super.putVehicle(vehicleId, putVehicleRequestDto);
    }
}
