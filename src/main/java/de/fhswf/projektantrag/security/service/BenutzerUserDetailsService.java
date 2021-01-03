package de.fhswf.projektantrag.security.service;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.service.BenutzerService;
import de.fhswf.projektantrag.security.details.BenutzerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BenutzerUserDetailsService implements UserDetailsService {

    @Autowired
    BenutzerService benutzerService;

    @Override
    public UserDetails loadUserByUsername(String benutzername) throws UsernameNotFoundException {
        Optional<BenutzerEntity> user = Optional.ofNullable(benutzerService.findByBenutzername(benutzername));
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + benutzername));

        return user.map(BenutzerUserDetails::new).get();

    }
}
