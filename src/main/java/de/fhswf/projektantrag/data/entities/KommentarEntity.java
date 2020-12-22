package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "kommentar", schema = "projektantrag", catalog = "")
public class KommentarEntity {
    private int id;
    private Integer projektId;
    private String text;

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
    @Column(name = "projekt_id", nullable = true)
    public Integer getProjektId() {
        return projektId;
    }

    public void setProjektId(Integer projektId) {
        this.projektId = projektId;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 100)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KommentarEntity that = (KommentarEntity) o;

        if (id != that.id) return false;
        if (projektId != null ? !projektId.equals(that.projektId) : that.projektId != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projektId != null ? projektId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
