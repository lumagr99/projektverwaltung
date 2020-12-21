package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.DozentEntity;

import java.util.List;
import java.util.Optional;

public class DozentDao implements Dao<DozentEntity> {


    @Override
    public Optional<DozentEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<DozentEntity> getAll() {
        return null;
    }

    @Override
    public void save(DozentEntity dozentEntity) {

    }

    @Override
    public void update(DozentEntity dozentEntity) {

    }

    @Override
    public void delete(DozentEntity dozentEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
