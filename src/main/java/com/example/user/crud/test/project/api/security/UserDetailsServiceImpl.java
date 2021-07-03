package com.example.user.crud.test.project.api.security;

import com.example.user.crud.test.project.api.model.User;
import com.example.user.crud.test.project.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Username was not found"));
        return new CurrentUser(currentUser);
    }
}
