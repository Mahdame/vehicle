package dev.maira.vehicle.mapper;

import static java.util.Objects.nonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import dev.maira.vehicle.entity.Vehicle;
import dev.maira.vehicle.openapi.model.*;
import dev.maira.vehicle.service.VehicleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapperDtoEntity {

    @Named("ConvertUUIDToString")
    default String convertUUIDToString(UUID id) {
        return id.toString();
    }

    @Named("convertDTOArrayToCollection")
    default Set<Vehicle> convertDTOArrayToCollection(List<String> ids) {
        return nonNull(ids) ? VehicleService.convertSetIdsToGetAll(ids) : new HashSet<>();
    }

    default ListVehiclesResponseDto convertVehicleCollectionToListDTO(Collection<Vehicle> entities) {
        ListVehiclesResponseDto responseDto = new ListVehiclesResponseDto();
        entities.forEach(vehicle -> responseDto.addContentItem(mapEntityToDto(vehicle)));
        responseDto.setTotalResults((long) entities.size());
        return responseDto;
    }

    @Mapping(source = "id", target = "vehicleId", qualifiedByName = "ConvertUUIDToString")
    GetVehicleResponseDto mapEntityToDto(Vehicle entity);
}