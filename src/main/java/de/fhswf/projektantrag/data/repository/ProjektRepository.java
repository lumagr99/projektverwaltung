package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.ProjektEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjektRepository extends CrudRepository<ProjektEntity, Integer> {


    List<ProjektEntity> getByStatusid(int statusid);
    List<ProjektEntity> getAllByStatusidAndTitel(int statusid, String titel);
    List<ProjektEntity> getAllByStatusid(int statusid);

    List<ProjektEntity> getAllByTitel(String title);
}
