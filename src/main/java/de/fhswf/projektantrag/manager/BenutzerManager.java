package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.service.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

/**
 * Verwaltet den aktuellen Benutzer.
 * @author Luca Graef
 * @version 1.0 03.01.2020
 */
@Component
@SessionScope
public class BenutzerManager {
    @Autowired
    BenutzerService benutzerService;

    BenutzerEntity current;

    public BenutzerManager(BenutzerService benutzerService){

    }

    @PostConstruct
    private void init(){
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //activeBenutzer = ((BenutzerUserDetails) auth.getPrincipal()).getId();
    }


}
