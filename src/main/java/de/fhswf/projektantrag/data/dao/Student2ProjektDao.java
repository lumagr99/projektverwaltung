package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.Student2ProjektEntity;

import java.util.List;
import java.util.Optional;

public class Student2ProjektDao implements Dao<Student2ProjektEntity> {
    @Override
    public Optional<Student2ProjektEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Student2ProjektEntity> getAll() {
        return null;
    }

    @Override
    public void save(Student2ProjektEntity student2ProjektEntity) {

    }

    @Override
    public void update(Student2ProjektEntity student2ProjektEntity) {

    }

    @Override
    public void delete(Student2ProjektEntity student2ProjektEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
