package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity(name = "ProjektEntity")
@Table(name = "projekt", schema = "projektantrag", catalog = "")
public class ProjektEntity {
    private int id;
    private String beschreibung;
    private String hintergrund;
    private String skizze;
    private int statusid;
    private String titel;
    private StatusEntity status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    public StatusEntity getStatus(){
        return status;
    }

    public void setStatus(StatusEntity statusEntity){
        this.status = statusEntity;
    }

    @Basic
    @Column(name = "beschreibung", nullable = false, length = 10000)
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Basic
    @Column(name = "hintergrund", nullable = false, length = 1000)
    public String getHintergrund() {
        return hintergrund;
    }

    public void setHintergrund(String hintergrund) {
        this.hintergrund = hintergrund;
    }

    @Basic
    @Column(name = "skizze", nullable = false, length = 1000)
    public String getSkizze() {
        return skizze;
    }

    public void setSkizze(String skizze) {
        this.skizze = skizze;
    }

    @Basic
    @Column(name = "statusid", nullable = false)
    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    @Basic
    @Column(name = "titel", nullable = false, length = 100)
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjektEntity that = (ProjektEntity) o;

        if (id != that.id) return false;
        //if (statusid != that.statusid) return false;
        if (beschreibung != null ? !beschreibung.equals(that.beschreibung) : that.beschreibung != null) return false;
        if (hintergrund != null ? !hintergrund.equals(that.hintergrund) : that.hintergrund != null) return false;
        if (skizze != null ? !skizze.equals(that.skizze) : that.skizze != null) return false;
        return titel != null ? titel.equals(that.titel) : that.titel == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (beschreibung != null ? beschreibung.hashCode() : 0);
        result = 31 * result + (hintergrund != null ? hintergrund.hashCode() : 0);
        result = 31 * result + (skizze != null ? skizze.hashCode() : 0);
        //result = 31 * result + statusid;
        result = 31 * result + (titel != null ? titel.hashCode() : 0);
        return result;
    }
}
