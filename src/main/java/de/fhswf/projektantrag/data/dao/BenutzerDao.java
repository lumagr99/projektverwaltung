package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;

import java.util.List;
import java.util.Optional;

public class BenutzerDao implements Dao<BenutzerEntity> {

    @Override
    public Optional<BenutzerEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<BenutzerEntity> getAll() {
        return null;
    }

    @Override
    public void save(BenutzerEntity benutzerEntity) {

    }

    @Override
    public void update(BenutzerEntity benutzerEntity) {

    }

    @Override
    public void delete(BenutzerEntity benutzerEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
