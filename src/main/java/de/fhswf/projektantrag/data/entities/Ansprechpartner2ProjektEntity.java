package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "ansprechpartner2projekt", schema = "projektantrag", catalog = "")
@IdClass(Ansprechpartner2ProjektEntityPK.class)
public class Ansprechpartner2ProjektEntity {
    private int ansprechpartnerid;
    private int projektId;

    @Id
    @Column(name = "ansprechpartnerid", nullable = false)
    public int getAnsprechpartnerid() {
        return ansprechpartnerid;
    }

    public void setAnsprechpartnerid(int ansprechpartnerid) {
        this.ansprechpartnerid = ansprechpartnerid;
    }

    @Id
    @Column(name = "ProjektID", nullable = false)
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

        Ansprechpartner2ProjektEntity that = (Ansprechpartner2ProjektEntity) o;

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
