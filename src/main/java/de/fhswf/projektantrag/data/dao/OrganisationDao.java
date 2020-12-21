package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.OrganisationEntity;

import java.util.List;
import java.util.Optional;

public class OrganisationDao implements Dao<OrganisationEntity>{
    @Override
    public Optional<OrganisationEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<OrganisationEntity> getAll() {
        return null;
    }

    @Override
    public void save(OrganisationEntity organisationEntity) {

    }

    @Override
    public void update(OrganisationEntity organisationEntity) {

    }

    @Override
    public void delete(OrganisationEntity organisationEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
