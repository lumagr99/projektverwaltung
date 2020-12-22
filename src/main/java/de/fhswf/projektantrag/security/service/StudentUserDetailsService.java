package de.fhswf.projektantrag.security.service;

import de.fhswf.projektantrag.data.entities.StudentEntity;
import de.fhswf.projektantrag.data.repository.StudentRepository;
import de.fhswf.projektantrag.security.details.StudentUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String benutzername) throws UsernameNotFoundException {
        Optional<StudentEntity> user = Optional.ofNullable(studentRepository.findByBenutzername(benutzername));
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + benutzername));

        return user.map(StudentUserDetails::new).get();

    }
}
