package dev.maira.vehicle.service;

import dev.maira.vehicle.entity.IEntity;
import dev.maira.vehicle.repository.IBaseRepository;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseEntityService<ID_CLASS extends Serializable, ENTITY extends IEntity<ID_CLASS>,
        REPOSITORY extends IBaseRepository<ENTITY, ID_CLASS>>
        implements IBaseEntityService<ID_CLASS, ENTITY> {

    protected final REPOSITORY repository;

    @Override
    public Optional<ENTITY> findById(ID_CLASS filter) {
        return repository.findById(filter);
    }

    @Override
    public Optional<ENTITY> findById(ENTITY filter) {
        return repository.findById(filter.getId());
    }

    @Override
    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY save(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public ENTITY update(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ID_CLASS id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(ENTITY obj) {
        repository.delete(obj);
    }

}
