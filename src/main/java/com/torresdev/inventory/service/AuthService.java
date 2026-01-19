package com.torresdev.inventory.service;

import com.torresdev.inventory.entity.Profile;
import com.torresdev.inventory.repository.ProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Profile login(String username, String password) {
        Profile profile = profileRepository
                .findByUsernameAndActiveTrue(username)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(password, profile.getPasswordHash())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return profile;
    }
}
