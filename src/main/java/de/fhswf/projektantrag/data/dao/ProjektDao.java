package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.ProjektEntity;

import java.util.List;
import java.util.Optional;

public class ProjektDao implements Dao<ProjektEntity> {
    @Override
    public Optional<ProjektEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<ProjektEntity> getAll() {
        return null;
    }

    @Override
    public void save(ProjektEntity projektEntity) {

    }

    @Override
    public void update(ProjektEntity projektEntity) {

    }

    @Override
    public void delete(ProjektEntity projektEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
