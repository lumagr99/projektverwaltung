package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "dozent", schema = "projektantrag", catalog = "")
public class DozentEntity {
    private int id;
    private String benutzername;
    private String nachname;
    private String password;
    private String vorname;

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
    @Column(name = "benutzername", nullable = false, length = 40, unique = true)
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    @Basic
    @Column(name = "nachname", nullable = false, length = 40)
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 65)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "vorname", nullable = false, length = 40)
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DozentEntity that = (DozentEntity) o;

        if (id != that.id) return false;
        if (benutzername != null ? !benutzername.equals(that.benutzername) : that.benutzername != null) return false;
        if (nachname != null ? !nachname.equals(that.nachname) : that.nachname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return vorname != null ? vorname.equals(that.vorname) : that.vorname == null;
    }

    @Override
    public int hashCode() {
        int result = id ^ (id >>> 32);
        result = 31 * result + (benutzername != null ? benutzername.hashCode() : 0);
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Id:" + this.getId()+
                " Vorname:" + this.getVorname() +
                " Nachname:" + this.getNachname() +
                " Benutzername:" +this.getBenutzername() +
                " Passwort:" + this.getPassword();
    }
}
