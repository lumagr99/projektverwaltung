package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.Ansprechpartner2ProjektEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ansprechpartner2ProjektRepository extends CrudRepository<Ansprechpartner2ProjektEntity, Integer> {
    List<Ansprechpartner2ProjektEntity> findAnsprechpartner2ProjektEntitiesByAnsprechpartnerid(int ansprechpartnerid);
    List<Ansprechpartner2ProjektEntity> findAnsprechpartner2ProjektEntitiesByProjektId(int projektId);
}
