package ru.gb.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "library_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "login",nullable = false,length = 100,unique = true)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "users_roles",length = 50)
    @ManyToMany(fetch = FetchType.EAGER,
    cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")}
    )
    private Set<Role> roles;

}
