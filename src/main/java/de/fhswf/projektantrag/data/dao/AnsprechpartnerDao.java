package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.AnsprechpartnerEntity;

import java.util.List;
import java.util.Optional;

public class AnsprechpartnerDao implements Dao<AnsprechpartnerEntity> {
    @Override
    public Optional<AnsprechpartnerEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<AnsprechpartnerEntity> getAll() {
        return null;
    }

    @Override
    public void save(AnsprechpartnerEntity ansprechpartnerEntity) {

    }

    @Override
    public void update(AnsprechpartnerEntity ansprechpartnerEntity) {

    }

    @Override
    public void delete(AnsprechpartnerEntity ansprechpartnerEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
