package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.StatusEntity;
import de.fhswf.projektantrag.data.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Verwaltet alle verfügbaren Stati.
 * @author Luca Graef
 * @version 1.0 03.01.2020
 */
@ApplicationScope
@Component
public class StatusManager {
    private List<StatusEntity> stati;

    @Autowired
    private StatusService statusService;

    StatusManager(StatusService statusService){

    }

    @PostConstruct
    private void init(){
        stati = statusService.getAll();
    }

    /**
     * Gibt ein StatusEntity anhand seiner ID zurück.
     * @param id
     * @return
     */
    public StatusEntity getStatus(int id){
        if(!(id > 0 && id <= stati.size())){
            throw new IllegalArgumentException();
        }
        return stati.get(id-1);
    }

    /**
     * Gibt eine Lister aller verfügbaren StatusEntities zurück.
     * @return
     */
    public List<StatusEntity> getStati(){
        return stati;
    }
}
