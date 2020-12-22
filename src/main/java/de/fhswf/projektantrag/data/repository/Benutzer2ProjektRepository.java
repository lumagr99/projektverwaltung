package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.Benutzer2ProjektEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Benutzer2ProjektRepository extends CrudRepository<Benutzer2ProjektEntity, Integer> {
    List<Benutzer2ProjektEntity> findBenutzer2ProjektEntitiesByBenutzerid(int benutzerid);
    List<Benutzer2ProjektEntity> findBenutzer2ProjektEntitiesByProjektid(int projektid);

}
