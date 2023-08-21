package com.example.gestionutilisateur_service.service;

import com.example.gestionutilisateur_service.dto.UserCredentialDTO;
import com.example.gestionutilisateur_service.dto.requests.AuthRequest;
import com.example.gestionutilisateur_service.entities.UserCredential;
import org.springframework.stereotype.Service;

public interface AuthService {

    String generateToken(String username);

    void validateToken(String token);

    UserCredential saveUser(UserCredentialDTO credential);

    String authenticate(AuthRequest authRequest);

}
