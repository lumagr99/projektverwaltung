package de.fhswf.projektantrag.security.details;

import org.springframework.security.core.userdetails.UserDetails;

public interface ProjektUserDetails extends UserDetails {
    int getId();
    int getRolle();
}
