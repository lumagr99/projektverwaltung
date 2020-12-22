package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends CrudRepository<BenutzerEntity, Integer> {
    BenutzerEntity findBenutzerEntityByBenutzername(String benutzername);
}
