package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "benutzer2projekt", schema = "projektantrag", catalog = "")
@IdClass(Benutzer2ProjektEntityPK.class)
public class Benutzer2ProjektEntity {
    private int benutzerid;
    private int projektid;

    @Id
    @Column(name = "benutzerid", nullable = false)
    public int getBenutzerid() {
        return benutzerid;
    }

    public void setBenutzerid(int benutzerid) {
        this.benutzerid = benutzerid;
    }

    @Id
    @Column(name = "projektid", nullable = false)
    public int getProjektid() {
        return projektid;
    }

    public void setProjektid(int projektid) {
        this.projektid = projektid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Benutzer2ProjektEntity that = (Benutzer2ProjektEntity) o;

        if (benutzerid != that.benutzerid) return false;
        return projektid == that.projektid;
    }

    @Override
    public int hashCode() {
        int result = benutzerid;
        result = 31 * result + projektid;
        return result;
    }
}
