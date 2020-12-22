package de.fhswf.projektantrag.security.details;

import de.fhswf.projektantrag.data.entities.StudentEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentUserDetails implements ProjectUserDetails {

    private final String userName;
    private final String password;
    private final int id;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public StudentUserDetails(StudentEntity studentEntity){
        if(studentEntity == null){
            throw new IllegalArgumentException();
        }

        this.userName = studentEntity.getBenutzername();
        this.password = studentEntity.getPasswort();
        this.authorities.add(new SimpleGrantedAuthority("Student"));
        this.id = studentEntity.getId();
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
}
