package de.fhswf.projektantrag.data.nutzer;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;

public class Student extends BenutzerEntity {

    public Student(BenutzerEntity benutzerEntity){
        if(benutzerEntity == null || benutzerEntity.getRollenEntity().getId() != 1){
            throw new IllegalArgumentException("Dieser Benutzer ist kein Student");
        }

        this.setBenutzername(benutzerEntity.getBenutzername());
        this.setVorname(benutzerEntity.getVorname());
        this.setNachname(benutzerEntity.getNachname());
        this.setPasswort(benutzerEntity.getPasswort());
        this.setId(benutzerEntity.getId());
        this.setOrganisationId(benutzerEntity.getOrganisationId());
        this.setRollenEntity(benutzerEntity.getRollenEntity());
    }

}
