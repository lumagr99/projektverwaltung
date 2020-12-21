package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.OrganisationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends CrudRepository<OrganisationEntity, Integer> {
}
