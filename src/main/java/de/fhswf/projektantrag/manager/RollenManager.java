package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.RollenEntity;
import de.fhswf.projektantrag.data.service.RollenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Verwaltet alle verfügbaren Rollen.
 * @author Luca Graef
 * @version 1.0 03.01.2020
 */
@Component
@ApplicationScope
public class RollenManager {

    private List<RollenEntity> rollenEntities;

    @Autowired
    private RollenService rollenService;


    RollenManager(RollenService rollenService){

    }

    @PostConstruct
    private void init(){
        rollenEntities = rollenService.getAll();
    }

    /**
     * Gibt eine Rolle anhand ihrer ID zurück.
     * @param rollenid
     * @return
     */
    public RollenEntity getRolle(int rollenid){
        if(!(rollenid <= rollenEntities.size() && rollenid > 0)){
            throw new IllegalArgumentException();
        }
        return rollenEntities.get(rollenid - 1);
    }

    /**
     * Gibt eine Liste aller verfügbaren Rollen zurück.
     * @return
     */
    public List<RollenEntity> getRollenEntities(){
        return rollenEntities;
    }



}
