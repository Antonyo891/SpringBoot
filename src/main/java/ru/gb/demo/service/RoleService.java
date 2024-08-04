package ru.gb.demo.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.demo.model.Role;
import ru.gb.demo.model.Roles;
import ru.gb.demo.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        roleRepository.save(role);
        return roleRepository.findById(role.getId()).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRole(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Нет роли с Id = " + "\"" + roleId + "\""));
    }

    @Override
    public Optional<Role> deleteRole(Role role) {
        Optional<Role> deleteRole = roleRepository.findById(role.getId());
        if (deleteRole.isPresent()) roleRepository.delete(role);
        return deleteRole;
    }

    @Override
    @Transactional
    public Role findRole(Roles name)
    {
        return roleRepository.findByName(name)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Нет роли с Id = " + "\"" + name.toString() + "\""));
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
