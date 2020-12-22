package de.fhswf.projektantrag.security.details;

import org.springframework.security.core.userdetails.UserDetails;

public interface ProjectUserDetails extends UserDetails {
    int getId();
}
