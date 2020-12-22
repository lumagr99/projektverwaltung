package de.fhswf.projektantrag.data.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Benutzer2ProjektEntityPK implements Serializable {
    private int benutzerid;
    private int projektid;

    @Column(name = "benutzerid", nullable = false)
    @Id
    public int getBenutzerid() {
        return benutzerid;
    }

    public void setBenutzerid(int benutzerid) {
        this.benutzerid = benutzerid;
    }

    @Column(name = "projektid", nullable = false)
    @Id
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

        Benutzer2ProjektEntityPK that = (Benutzer2ProjektEntityPK) o;

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
