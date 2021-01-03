package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.StatusEntity;
import de.fhswf.projektantrag.data.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.List;

@ApplicationScope
@Component
public class StatusManager {
    private List<StatusEntity> stati;

    @Autowired
    StatusService statusService;

    public StatusManager(StatusService statusService){

    }

    @PostConstruct
    private void init(){
        stati = statusService.getAll();
    }

    public StatusEntity getStatus(int id){
        if(!(id > 0 && id < stati.size())){
            throw new IllegalArgumentException();
        }
        return stati.get(id);
    }

    public List<StatusEntity> getStati(){
        return stati;
    }
}
