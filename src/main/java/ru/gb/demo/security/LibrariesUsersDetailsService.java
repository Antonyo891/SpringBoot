package ru.gb.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.demo.service.UserServiceInterface;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LibrariesUsersDetailsService implements UserDetailsService {
    private final UserServiceInterface userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.gb.demo.model.User user = userService.findUser(username);
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role->new SimpleGrantedAuthority(role.toString()))
                .toList();
        return new User(user.getLogin(),user.getPassword(),authorities);
    }
}
