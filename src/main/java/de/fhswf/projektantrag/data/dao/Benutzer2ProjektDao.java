package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.Benutzer2ProjektEntity;

import java.util.List;
import java.util.Optional;

public class Benutzer2ProjektDao implements Dao<Benutzer2ProjektEntity> {
    @Override
    public Optional<Benutzer2ProjektEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Benutzer2ProjektEntity> getAll() {
        return null;
    }

    @Override
    public void save(Benutzer2ProjektEntity benutzer2ProjektEntity) {

    }

    @Override
    public void update(Benutzer2ProjektEntity benutzer2ProjektEntity) {

    }

    @Override
    public void delete(Benutzer2ProjektEntity benutzer2ProjektEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
