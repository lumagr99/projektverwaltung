package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rollen", schema = "projektantrag", catalog = "")
public class RollenEntity {
    private int id;
    private String bezeichnung;

    private List<BenutzerEntity> benutzer;

    @OneToMany(targetEntity = BenutzerEntity.class,mappedBy = "rollenEntity",fetch = FetchType.EAGER)
    public List<BenutzerEntity> getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(List<BenutzerEntity> benutzer) {
        this.benutzer = benutzer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Bezeichnung", nullable = false, length = 40)
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RollenEntity that = (RollenEntity) o;

        if (id != that.id) return false;
        return bezeichnung != null ? bezeichnung.equals(that.bezeichnung) : that.bezeichnung == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bezeichnung != null ? bezeichnung.hashCode() : 0);
        return result;
    }
}
