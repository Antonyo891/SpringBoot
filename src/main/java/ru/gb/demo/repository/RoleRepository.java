package ru.gb.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.demo.model.Role;
import ru.gb.demo.model.Roles;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(Roles name);
}
