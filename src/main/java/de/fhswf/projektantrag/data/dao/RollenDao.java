package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.RollenEntity;

import java.util.List;
import java.util.Optional;

public class RollenDao implements Dao<RollenEntity> {
    @Override
    public Optional<RollenEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<RollenEntity> getAll() {
        return null;
    }

    @Override
    public void save(RollenEntity rollenEntity) {

    }

    @Override
    public void update(RollenEntity rollenEntity) {

    }

    @Override
    public void delete(RollenEntity rollenEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
