package ru.gb.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false,length = 100,unique = true)
    private Roles name;

//    @Column(name = "users")
//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE},
//            mappedBy = "roles")
//    @JsonIgnore
//    private Set<User> users;

}
