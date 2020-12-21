package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.StudentDao;
import de.fhswf.projektantrag.data.entities.StudentEntity;
import de.fhswf.projektantrag.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService extends StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<StudentEntity> get(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<StudentEntity> getAll() {
        List<StudentEntity> list = StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    @Override
    public void update(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    @Override
    public void delete(StudentEntity studentEntity) {
        studentRepository.delete(studentEntity);
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    public StudentEntity findByBenutzername(String benutzername){
        return studentRepository.findByBenutzername(benutzername);
    }
}
