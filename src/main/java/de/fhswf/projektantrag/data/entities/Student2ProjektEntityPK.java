package de.fhswf.projektantrag.data.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Student2ProjektEntityPK implements Serializable {
    private int studentId;
    private int projektId;

    @Column(name = "StudentID", nullable = false)
    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Column(name = "ProjektID", nullable = false)
    @Id
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

        Student2ProjektEntityPK that = (Student2ProjektEntityPK) o;

        if (studentId != that.studentId) return false;
        return projektId == that.projektId;
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + projektId;
        return result;
    }
}
