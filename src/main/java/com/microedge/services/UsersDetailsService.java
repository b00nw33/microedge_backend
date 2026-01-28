package com.microedge.services;

import com.microedge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Manage UsernameNotFound exception at GlobalExceptionHandler
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
