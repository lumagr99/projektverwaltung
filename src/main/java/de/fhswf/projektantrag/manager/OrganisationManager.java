package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.entities.OrganisationEntity;
import de.fhswf.projektantrag.data.service.BenutzerService;
import de.fhswf.projektantrag.data.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

/**
 * Verwaltet den Zugriff auf Organisationen session spezifisch.
 * @author Luca Graef
 * @version 1.0 31.12.2020
 */

@SessionScope
@Component
public class OrganisationManager {
    private OrganisationEntity current;
    private List<BenutzerEntity> ansprechpartner;

    @Autowired
    OrganisationService organisationService;

    @Autowired
    BenutzerService benutzerService;

    public OrganisationManager(OrganisationService organisationService){

    }

    /**
     * Organisation selektieren.
     * @param organisationID
     */
    public void select(int organisationID){
        current = organisationService.get(organisationID).get();
        initAnsprechpartner();
    }

    /**
     * Gibt alle Ansprechpartner zurück.
     * @return
     */
    public List<BenutzerEntity> getAnsprechpartner(){
        return ansprechpartner;
    }

    /**
     * Fügt einen Ansprechpartner hinzu.
     * @param benutzerEntity
     */
    public void addAnsprechpartner(BenutzerEntity benutzerEntity){
        if(benutzerEntity == null){
            throw new IllegalArgumentException();
        }

        if(benutzerEntity.getRollenEntity().getId() != 3){
            throw new IllegalArgumentException("Ein Benutzer mit dieser Rolle kann nicht hinzugefügt werden!");
        }

        boolean inList = false;

        for (BenutzerEntity entity : ansprechpartner) {
            if(entity.getId() == benutzerEntity.getId()){
                inList = true;
                break;
            }
        }

        if(!inList){
            ansprechpartner.add(benutzerEntity);
            benutzerEntity.setOrganisationId(current.getId());
            benutzerService.save(benutzerEntity);
        }
        initAnsprechpartner();
    }

    /**
     * Entfernt einen Ansprechpartner.
     * @param benutzerEntity
     */
    public void removeAnsprechpartner(BenutzerEntity benutzerEntity){
        boolean inList = false;
        for (BenutzerEntity entity : ansprechpartner) {
            if(entity.getId() == benutzerEntity.getId()){
                inList = true;
                break;
            }
        }

        if(inList){
            ansprechpartner.remove(benutzerEntity);
            benutzerEntity.setOrganisationId(null);
            benutzerService.save(benutzerEntity);
        }
        initAnsprechpartner();
    }

    /**
     * Initialisiert die Ansprechpartnerliste.
     */
    private void initAnsprechpartner(){
        ansprechpartner = benutzerService.findBenutzerByOrganisationID(current.getId());
    }

    /**
     * Gibt eine Liste aller Organisationen zurück.
     * @return
     */
    public List<OrganisationEntity> getAll(){
        return organisationService.getAll();
    }
}
