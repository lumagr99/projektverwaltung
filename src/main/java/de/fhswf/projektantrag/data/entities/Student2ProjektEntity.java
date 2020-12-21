package de.fhswf.projektantrag.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "student2projekt", schema = "projektantrag", catalog = "")
@IdClass(Student2ProjektEntityPK.class)
public class Student2ProjektEntity {
    private int studentId;
    private int projektId;

    @Id
    @Column(name = "StudentID", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "ProjektID", nullable = false)
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

        Student2ProjektEntity that = (Student2ProjektEntity) o;

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
