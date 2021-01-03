package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.ProjektEntity;
import de.fhswf.projektantrag.data.entities.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjektRepository extends CrudRepository<ProjektEntity, Integer> {


    //List<ProjektEntity> getByStatusid(int statusid);
    //List<ProjektEntity> getAllByStatusidAndTitel(int statusid, String titel);
    List<ProjektEntity> getAllByStatus(StatusEntity statusEntity);
    List<ProjektEntity> getAllByStatusAndTitel(StatusEntity statusEntity, String title);

    List<ProjektEntity> getAllByTitel(String title);
}
