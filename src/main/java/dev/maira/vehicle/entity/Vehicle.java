package dev.maira.vehicle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class Vehicle implements IEntity<UUID> {
    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "vehicle_customer_owner")
    private String customerOwner;

    @Column(name = "telemetry_profile")
    private String telemetryProfile;

    @Column(name = "current_driver")
    private String currentDriver;

    @Column(name = "number_plate")
    private String numberPlate;

    @Column(name = "vin")
    private String vin;

    @Column(name = "color")
    private String color;
}