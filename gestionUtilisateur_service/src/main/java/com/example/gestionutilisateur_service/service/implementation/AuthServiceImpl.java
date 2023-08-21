package com.example.gestionutilisateur_service.service.implementation;

import com.example.gestionutilisateur_service.dto.UserCredentialDTO;
import com.example.gestionutilisateur_service.dto.requests.AuthRequest;
import com.example.gestionutilisateur_service.entities.UserCredential;
import com.example.gestionutilisateur_service.repository.UserRepository;
import com.example.gestionutilisateur_service.service.AuthService;
import com.example.gestionutilisateur_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserCredential saveUser(UserCredentialDTO credential) {
        log.info("credential" + credential);
        UserCredential user = new UserCredential();
        user.setUsername(credential.getUsername());
        user.setEmail(credential.getEmail());
        user.setPassword(passwordEncoder.encode(credential.getPassword()));
        return userRepository.save(user);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public String authenticate(AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {

            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid access");
        }
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
