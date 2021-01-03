package de.fhswf.projektantrag.security.details;

import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BenutzerUserDetails implements ProjektUserDetails {

    private final String userName;
    private final String password;
    private final int id;
    private final int role;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public BenutzerUserDetails(BenutzerEntity benutzerEntity){
        if(benutzerEntity == null){
            throw new IllegalArgumentException();
        }

        this.userName = benutzerEntity.getBenutzername();
        this.password = benutzerEntity.getPasswort();
        this.authorities.add(new SimpleGrantedAuthority("Student"));
        this.id = benutzerEntity.getId();
        this.role = benutzerEntity.getRollenEntity().getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getRolle() {
        return this.role;
    }
}
