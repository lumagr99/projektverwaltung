package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<StatusEntity, Integer> {
}
