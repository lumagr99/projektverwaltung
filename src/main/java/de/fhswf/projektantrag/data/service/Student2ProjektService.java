package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.Student2ProjektDao;
import de.fhswf.projektantrag.data.entities.Student2ProjektEntity;
import de.fhswf.projektantrag.data.repository.Student2ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Student2ProjektService extends Student2ProjektDao {
    @Autowired
    Student2ProjektRepository student2ProjektRepository;

    @Override
    public Optional<Student2ProjektEntity> get(int id) {
        return student2ProjektRepository.findById(id);
    }

    @Override
    public List<Student2ProjektEntity> getAll() {
        List<Student2ProjektEntity> list = StreamSupport.stream(student2ProjektRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(Student2ProjektEntity student2ProjektEntity) {
        student2ProjektRepository.save(student2ProjektEntity);
    }

    @Override
    public void update(Student2ProjektEntity student2ProjektEntity) {
        this.save(student2ProjektEntity);
    }

    @Override
    public void delete(Student2ProjektEntity student2ProjektEntity) {
        student2ProjektRepository.delete(student2ProjektEntity);
    }

    @Override
    public void delete(int id) {
        student2ProjektRepository.deleteById(id);
    }

    public List<Student2ProjektEntity>  findStudentsByProjectID(int projektid){
        List<Student2ProjektEntity> list = StreamSupport.stream(student2ProjektRepository.findStudent2ProjektEntitiesByProjektId(projektid).spliterator(), false).collect(Collectors.toList());
        return list;
    }

    public List<Student2ProjektEntity>  findProjektsByStudentID(int studentid){
        List<Student2ProjektEntity> list = StreamSupport.stream(student2ProjektRepository.findStudent2ProjektEntitiesByStudentId(studentid).spliterator(), false).collect(Collectors.toList());
        return list;
    }


}
