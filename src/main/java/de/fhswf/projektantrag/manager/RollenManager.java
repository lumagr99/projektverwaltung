package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.RollenEntity;
import de.fhswf.projektantrag.data.service.RollenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ApplicationScope
public class RollenManager {

    private List<RollenEntity> rollenEntities;

    @Autowired
    RollenService rollenService;

    RollenManager(RollenService rollenService){

    }

    @PostConstruct
    private void init(){
        rollenEntities = rollenService.getAll();
    }

    public RollenEntity getRolle(int rollenid){
        if(!(rollenid <= rollenEntities.size() && rollenid > 0)){
            throw new IllegalArgumentException();
        }
        return rollenEntities.get(rollenid - 1);
    }



}
