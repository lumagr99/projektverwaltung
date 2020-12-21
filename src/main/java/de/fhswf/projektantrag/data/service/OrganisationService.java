package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.OrganisationDao;
import de.fhswf.projektantrag.data.entities.OrganisationEntity;
import de.fhswf.projektantrag.data.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrganisationService extends OrganisationDao {
    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public Optional<OrganisationEntity> get(int id) {
        return organisationRepository.findById(id);
    }

    @Override
    public List<OrganisationEntity> getAll() {
        List<OrganisationEntity> list = StreamSupport.stream(organisationRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(OrganisationEntity organisationEntity) {
        organisationRepository.save(organisationEntity);
    }

    @Override
    public void update(OrganisationEntity organisationEntity) {
        this.save(organisationEntity);
    }

    @Override
    public void delete(OrganisationEntity organisationEntity) {
        organisationRepository.delete(organisationEntity);
    }

    @Override
    public void delete(int id) {
        organisationRepository.deleteById(id);
    }
}
