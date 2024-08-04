package ru.gb.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.demo.model.User;

import java.util.List;
import java.util.Optional;
@Service
public interface UserServiceInterface {
    @Transactional
    public User findUser(String usersLogin);
    public User addUser(User user);

    public User update(User user);

    List<User> getUsers();
}
