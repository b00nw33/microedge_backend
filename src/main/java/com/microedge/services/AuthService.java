package com.microedge.services;

import com.microedge.dto.UserDto;
import com.microedge.exceptions.EmailAlreadyExistsException;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.models.User;
import com.microedge.repositories.UserRepository;
import com.microedge.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public User signUp(User user) throws EmailAlreadyExistsException {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Please use another email.");
        }

        // User _user = User.builder()
        // .email(user.getEmail())
        // .password(passwordEncoder.encode((user.getPassword())))
        // .build();

        // System.out.println(passwordEncoder.encode(user.getPassword()));

        User _user = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName()) // ← ADD THIS
                .lastName(user.getLastName()) // ← ADD THIS
                .role(User.Role.TRAINEE) // ← Enforce default role
                // .role(User.Role.TRAINER) // ← Enforce default role
                .build();

        return userRepository.save(_user);
    }

    @Transactional
    public UserDto signIn(User user) throws ResourceAccessException {

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(user.getEmail(),
                user.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

        /**
         * SecurityContextHolder.getContext().setAuthentication(authenticationResponse)
         * - logs the authenticated user
         * authenticationResponse.getPrincipal() - returns an Object (class:
         * UserDetails)
         * Cast returned UserDetails to User entity (userDetails doesn't have access
         * modifier for "userName")
         * Within userDetails "userName" ≠ attribute "username")
         * The typecasting allows the extraction of: userName (≠ username), email and
         * role
         */

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        User _user = (User) authenticationResponse.getPrincipal();

        String token = jwtUtils.generateToken(_user.getFirstName(), _user.getLastName(), _user);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), _user.getFirstName(), _user.getLastName(), _user);
        Long expirationTime = jwtUtils.extractExpirationTime(token);

        UserDto userDto = UserDto.builder()
                .firstName(_user.getFirstName()) // Return athenticated user userName
                .lastName(_user.getLastName()) // Return athenticated user userName
                .email(_user.getUsername()) // Return athenticated user email, akin to UserDetails.getUsername());
                .token(token) // Return prepared token
                .refreshToken(refreshToken) // Return prepared refresh token
                .expirationTime(expirationTime) // Return prepared expiry
                .message("success") // Return "success" as a message
                .role(_user.getRole()) // Return authenticated user's role
                .build();

        return userDto;
    }

    public UserDto update(User user, MultipartFile image) throws IOException, ResourceNotFoundException {

        // Obtain the user's identity from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        // Fetch the managed user
        User existingUser = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Map ONLY the fields you want to allow updating
        if (user.getFirstName() != null)
            existingUser.setFirstName(user.getFirstName());

        if (user.getLastName() != null)
            existingUser.setLastName(user.getLastName());

        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());

        if (user.getPassword() != null)
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

        // role should not be updated by an end user

        userRepository.save(existingUser);

        String token = jwtUtils.generateToken(existingUser.getFirstName(), existingUser.getLastName(), existingUser);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), existingUser.getFirstName(), existingUser.getLastName(), existingUser);
        Long expirationTime = jwtUtils.extractExpirationTime(token);

        // package the data to return
        UserDto userDto = UserDto.builder()
                .id(existingUser.getId())
                .role(existingUser.getRole())
                .firstName(existingUser.getFirstName()) // Return athenticated user userName
                .lastName(existingUser.getLastName()) // Return athenticated user userName
                .email(existingUser.getEmail())
                .token(token) // Return prepared token
                .refreshToken(refreshToken) // Return prepared refresh token
                .expirationTime(expirationTime) // Return prepared expiry
                .message("update success")
                .build();

        // The following are not returned as updates
        // - role
        // - token
        // - refreshToken
        // - expiration

        return userDto;
    }

}
