package de.fhswf.projektantrag.data.repository;

import de.fhswf.projektantrag.data.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    StudentEntity findByBenutzername(String benutzername);
}
