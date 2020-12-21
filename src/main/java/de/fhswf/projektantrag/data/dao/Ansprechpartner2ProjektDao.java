package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.Ansprechpartner2ProjektEntity;

import java.util.List;
import java.util.Optional;

public class Ansprechpartner2ProjektDao implements Dao<Ansprechpartner2ProjektEntity>{


    @Override
    public Optional<Ansprechpartner2ProjektEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Ansprechpartner2ProjektEntity> getAll() {
        return null;
    }

    @Override
    public void save(Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity) {

    }

    @Override
    public void update(Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity) {

    }

    @Override
    public void delete(Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
