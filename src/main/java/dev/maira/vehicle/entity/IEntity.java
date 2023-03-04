package dev.maira.vehicle.entity;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {

    public T getId();
}
