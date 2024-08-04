package ru.gb.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.demo.model.Role;
import ru.gb.demo.model.Roles;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleServiceInterface {
    public Role addRole(Role role);
    public List<Role> findAll();
    @Transactional
    public Role findRole(Long roleId);
    public Optional<Role> deleteRole(Role role);
    @Transactional
    public Role findRole(Roles name);
    public void deleteAll();
}
