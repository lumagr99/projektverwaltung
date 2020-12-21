package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.AnsprechpartnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsprechpartnerRepository extends CrudRepository<AnsprechpartnerEntity, Integer> {


}
