package ru.gb.demo.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.demo.model.User;
import ru.gb.demo.repository.UserRepository;
import ru.gb.demo.security.LibrariesUsersPasswordEncoder;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibrariesUsersPasswordEncoder librariesUsersPasswordEncoder;
    @Override
    @Transactional
    public User findUser(String usersLogin) throws UsernameNotFoundException{
        //        user.getRoles();
        return userRepository.findByLogin(usersLogin)
                .orElseThrow(()-> new UsernameNotFoundException(usersLogin));
    }

    @Override
    public User addUser(User user) {
        String password = librariesUsersPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        return userRepository.findById(user.getId()).orElse(null);
    }

    @Override
    @Transactional
    public User update(User user) {
        User updateUser = userRepository.findById(user.getId()).orElse(null);
        assert updateUser != null;
        updateUser.setPassword(librariesUsersPasswordEncoder.encode(user.getPassword()));
        updateUser.setRoles(user.getRoles());
        updateUser.setLogin(user.getLogin());
        userRepository.save(updateUser);
        userRepository.flush();
        return findUser(updateUser.getLogin());
    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }
}
