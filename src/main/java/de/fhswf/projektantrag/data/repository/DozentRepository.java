package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.DozentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DozentRepository extends CrudRepository<DozentEntity, Integer> {
    List<DozentEntity> findDozentEntitiesByBenutzername(String benutzername);
    DozentEntity findDozentEntityByBenutzername(String benutzername);
}
