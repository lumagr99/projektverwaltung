package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.StatusEntity;

import java.util.List;
import java.util.Optional;

public class StatusDao implements Dao<StatusEntity> {
    @Override
    public Optional<StatusEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<StatusEntity> getAll() {
        return null;
    }

    @Override
    public void save(StatusEntity statusEntity) {

    }

    @Override
    public void update(StatusEntity statusEntity) {

    }

    @Override
    public void delete(StatusEntity statusEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
