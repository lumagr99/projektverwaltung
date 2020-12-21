package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.Student2ProjektEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student2ProjektRepository extends CrudRepository<Student2ProjektEntity, Integer> {
    List<Student2ProjektEntity> findStudent2ProjektEntitiesByStudentId(int studentid);
    List<Student2ProjektEntity> findStudent2ProjektEntitiesByProjektId(int projektId);
}
