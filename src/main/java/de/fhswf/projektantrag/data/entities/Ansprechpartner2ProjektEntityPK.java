package de.fhswf.projektantrag.data.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Ansprechpartner2ProjektEntityPK implements Serializable {
    private int ansprechpartnerid;
    private int projektId;

    @Column(name = "ansprechpartnerid", nullable = false)
    @Id
    public int getAnsprechpartnerid() {
        return ansprechpartnerid;
    }

    public void setAnsprechpartnerid(int ansprechpartnerid) {
        this.ansprechpartnerid = ansprechpartnerid;
    }

    @Column(name = "ProjektID", nullable = false)
    @Id
    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ansprechpartner2ProjektEntityPK that = (Ansprechpartner2ProjektEntityPK) o;

        if (ansprechpartnerid != that.ansprechpartnerid) return false;
        return projektId == that.projektId;
    }

    @Override
    public int hashCode() {
        int result = ansprechpartnerid;
        result = 31 * result + projektId;
        return result;
    }
}
