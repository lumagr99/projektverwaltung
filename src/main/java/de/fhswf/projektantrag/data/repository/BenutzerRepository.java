package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.entities.RollenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenutzerRepository extends CrudRepository<BenutzerEntity, Integer> {
    BenutzerEntity findBenutzerEntityByBenutzername(String benutzername);
    //List<BenutzerEntity> findBenutzerEntitiesByRolleId(int rollenid);
    List<BenutzerEntity> findBenutzerEntitiesByRollenEntity(RollenEntity rollenEntity);
    List<BenutzerEntity> findBenutzerEntitiesByOrganisationId(int organisationid);
    List<BenutzerEntity> findBenutzerEntitiesByRollenEntityAndIdNotIn(RollenEntity rollenEntity, List<Integer> benutzerIds);
}
