package de.fhswf.projektantrag.manager;

import de.fhswf.projektantrag.data.entities.Benutzer2ProjektEntity;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.entities.KommentarEntity;
import de.fhswf.projektantrag.data.entities.ProjektEntity;
import de.fhswf.projektantrag.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@Component
public class ProjektManager {

    private ProjektEntity current;
    private List<BenutzerEntity> studenten;
    private List<BenutzerEntity> ansprechpartner;
    private List<KommentarEntity> kommentare;

    @Autowired
    BenutzerService benutzerService;

    @Autowired
    ProjektService projektService;

    @Autowired
    RollenService rollenService;

    @Autowired
    StatusService statusService;

    @Autowired
    OrganisationService organisationService;

    @Autowired
    KommentarService kommentarService;

    @Autowired
    Benutzer2ProjektService benutzer2ProjektService;

    public ProjektManager(KommentarService kommentarService, BenutzerService benutzerService,
                          OrganisationService organisationService, StatusService statusService,
                          RollenService rollenService, ProjektService projektService,
                          Benutzer2ProjektService benutzer2ProjektService){

    }

    public void select(int id){
        current = projektService.get(id).get();

        if(current == null){
            ProjektEntity projektEntity = new ProjektEntity();
            projektEntity.setStatusid(1);
            projektEntity.setTitel(" ");
            projektEntity.setSkizze(" ");
            projektEntity.setHintergrund(" ");
            projektEntity.setBeschreibung(" ");
            projektService.save(projektEntity);

            //Benutzer2ProjektEntity benutzer2ProjektEntity = new Benutzer2ProjektEntity();
            //benutzer2ProjektEntity.setProjektid(projektEntity.getId());
            //benutzer2ProjektEntity.setBenutzerid(activeBenutzer.getId());
            //benutzer2ProjektService.save(benutzer2ProjektEntity);
            current = projektEntity;
        }

        initStudents();
        initAnsprechpartner();
        initKommentare();
    }

    public void update(){
        if(current != null){
            projektService.update(current);
        }

    }

    public void delete(){
        if(current != null){
            projektService.delete(current);
        }
    }

    public List<BenutzerEntity> getStudents(){
        return studenten;
    }

    public void addStudent(BenutzerEntity benutzerEntity){
        if(benutzerEntity.getRolleId() != 1){
            throw new IllegalArgumentException("Diese Rolle kann nicht als Student zugewiesen werden!");
        }

        boolean inList = false;
        for (BenutzerEntity entity : studenten) {
            if(entity.getId() == benutzerEntity.getId()){
                inList = true;
            }
        }

        if(!inList){
            Benutzer2ProjektEntity benutzer2ProjektEntity = new Benutzer2ProjektEntity();
            benutzer2ProjektEntity.setBenutzerid(benutzerEntity.getId());
            benutzer2ProjektEntity.setProjektid(current.getId());
            benutzer2ProjektService.save(benutzer2ProjektEntity);
            studenten.add(benutzerEntity);
        }
    }

    public List<BenutzerEntity> getAnsprechpartner(){
        return ansprechpartner;
    }

    public void addAnsprechpartner(BenutzerEntity benutzerEntity){
        boolean inList = false;
        for (BenutzerEntity temp : ansprechpartner) {
            if(temp.getId() == benutzerEntity.getId()){
                inList = true;
                break;
            }
        }

        if (!inList){
            Benutzer2ProjektEntity benutzer2ProjektEntity = new Benutzer2ProjektEntity();
            benutzer2ProjektEntity.setBenutzerid(benutzerEntity.getId());
            benutzer2ProjektEntity.setProjektid(current.getId());
            benutzer2ProjektService.save(benutzer2ProjektEntity);
            ansprechpartner.add(benutzerEntity);
        }
    }

    public List<KommentarEntity> getKommentare(){
        return kommentare;
    }

    public void addKommentar(KommentarEntity kommentarEntity){
        kommentarService.save(kommentarEntity);
        kommentare.add(kommentarEntity);
    }

    private void initStudents(){
        studenten = getBenutzer(1);

    }

    private void initAnsprechpartner(){
        ansprechpartner = getBenutzer(3);
    }

    private void initKommentare(){
        kommentare = kommentarService.findKommentareByProjektID(current.getId());
    }

    private List<BenutzerEntity> getBenutzer(int roleID) {
        List<Benutzer2ProjektEntity> helper = benutzer2ProjektService.findBenutzer2ProjektByProjektID(current.getId());
        List<BenutzerEntity> list = new ArrayList<>();

        for (Benutzer2ProjektEntity benutzer2ProjektEntity : helper) {
            BenutzerEntity curr = benutzerService.get(benutzer2ProjektEntity.getBenutzerid()).get();
            if (curr.getRolleId() == roleID) {
                list.add(curr);
            }
        }
        return list;
    }


    public ProjektEntity getCurrent() {
        return current;
    }
}
