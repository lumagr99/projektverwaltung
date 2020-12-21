package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "status", schema = "projektantrag", catalog = "")
public class StatusEntity {
    private int id;
    private String bezeichnung;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bezeichnung", nullable = false, length = 40)
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

        StatusEntity that = (StatusEntity) o;

        if (id != that.id) return false;
        return bezeichnung != null ? bezeichnung.equals(that.bezeichnung) : that.bezeichnung == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bezeichnung != null ? bezeichnung.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() +
                " Bezeichnung: " + this.getBezeichnung();
    }
}
