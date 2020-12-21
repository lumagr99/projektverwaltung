package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "student", schema = "projektantrag", catalog = "")
public class StudentEntity {
    private int id;
    private String nachname;
    private String passwort;
    private String vorname;
    private String benutzername;

    public StudentEntity(String benutzername, String passwort){
        if(benutzername == null || passwort == null){
            throw new IllegalArgumentException();
        }

        this.benutzername = benutzername; this.passwort = passwort;
    }

    public StudentEntity(){}

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
    @Column(name = "nachname", length = 40)
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "passwort", length = 64)
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Basic
    @Column(name = "vorname", length = 40)
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Basic
    @Column(name = "benutzername", length = 40, unique = true)
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (id != that.id) return false;
        if (nachname != null ? !nachname.equals(that.nachname) : that.nachname != null) return false;
        if (passwort != null ? !passwort.equals(that.passwort) : that.passwort != null) return false;
        if (vorname != null ? !vorname.equals(that.vorname) : that.vorname != null) return false;
        return benutzername != null ? benutzername.equals(that.benutzername) : that.benutzername == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (passwort != null ? passwort.hashCode() : 0);
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (benutzername != null ? benutzername.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() +
                " Vorname: " + this.getVorname() +
                " Nachname: " + this.getNachname() +
                " Benutzername: " + this.getPasswort() +
                " Passwort: " + this.getPasswort();
    }
}
