package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "benutzer", schema = "projektantrag", catalog = "")
public class BenutzerEntity {
    private int id;
    private String vorname;
    private String nachname;
    private String benutzername;
    private String passwort;
    private Integer organisationId;

    private RollenEntity rollenEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "rolleid", referencedColumnName = "id", nullable = false)
    public RollenEntity getRollenEntity(){
        return rollenEntity;
    }

    public void setRollenEntity(RollenEntity rollenEntity){
        this.rollenEntity = rollenEntity;
    }

    @Basic
    @Column(name = "Vorname", nullable = false, length = 40)
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Basic
    @Column(name = "Nachname", nullable = false, length = 40)
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "Benutzername", nullable = false, length = 40, unique = true)
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    @Basic
    @Column(name = "Passwort", nullable = false, length = 64)
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Basic
    @Column(name = "OrganisationID", nullable = true)
    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BenutzerEntity that = (BenutzerEntity) o;

        if (id != that.id) return false;
        //if (rolleId != that.rolleId) return false;
        if (vorname != null ? !vorname.equals(that.vorname) : that.vorname != null) return false;
        if (nachname != null ? !nachname.equals(that.nachname) : that.nachname != null) return false;
        if (benutzername != null ? !benutzername.equals(that.benutzername) : that.benutzername != null) return false;
        if (passwort != null ? !passwort.equals(that.passwort) : that.passwort != null) return false;
        return organisationId != null ? organisationId.equals(that.organisationId) : that.organisationId == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + rolleId;
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (benutzername != null ? benutzername.hashCode() : 0);
        result = 31 * result + (passwort != null ? passwort.hashCode() : 0);
        result = 31 * result + (organisationId != null ? organisationId.hashCode() : 0);
        return result;
    }
}


