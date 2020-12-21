package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.KommentarEntity;

import java.util.List;
import java.util.Optional;

public class KommentarDao implements Dao<KommentarEntity> {
    @Override
    public Optional<KommentarEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<KommentarEntity> getAll() {
        return null;
    }

    @Override
    public void save(KommentarEntity kommentarEntity) {

    }

    @Override
    public void update(KommentarEntity kommentarEntity) {

    }

    @Override
    public void delete(KommentarEntity kommentarEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
