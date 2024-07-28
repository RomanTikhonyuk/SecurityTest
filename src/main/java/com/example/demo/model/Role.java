package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Entity
@Data
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column (name = "role", unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}
