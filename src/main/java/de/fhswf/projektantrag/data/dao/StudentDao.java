package de.fhswf.projektantrag.data.dao;

import de.fhswf.projektantrag.data.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

public class StudentDao implements Dao<StudentEntity> {
    @Override
    public Optional<StudentEntity> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<StudentEntity> getAll() {
        return null;
    }

    @Override
    public void save(StudentEntity studentEntity) {

    }

    @Override
    public void update(StudentEntity studentEntity) {

    }

    @Override
    public void delete(StudentEntity studentEntity) {

    }

    @Override
    public void delete(int id) {

    }
}
