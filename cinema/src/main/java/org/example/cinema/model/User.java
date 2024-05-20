package org.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "cinema_user")
@Data
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    private String name;

    private String surname;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired =  true;
    private boolean isEnabled = true;


    @OneToMany
    private List<TicketCart> carts;

    public User(String username, String name, String surname, String password, UserType userType) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.userType= userType;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(userType);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
