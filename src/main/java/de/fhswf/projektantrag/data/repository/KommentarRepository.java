package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.KommentarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KommentarRepository extends CrudRepository<KommentarEntity, Integer> {
    List<KommentarEntity> findAllByProjektId(int projekt_id);
}
