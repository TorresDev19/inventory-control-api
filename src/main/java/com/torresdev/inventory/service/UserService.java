package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.user.UserRequestDTO;
import com.torresdev.inventory.dto.user.UserResponseDTO;
import com.torresdev.inventory.dto.user.UserUpdateDTO;
import com.torresdev.inventory.entity.Profile;
import com.torresdev.inventory.repository.ProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserResponseDTO> listAll() {
        return profileRepository.findAll().stream()
                .filter(Profile::getActive)
                .map(this::toDTO)
                .toList();
    }

    public UserResponseDTO findById(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!profile.getActive()) {
            throw new RuntimeException("User not found");
        }
        return toDTO(profile);
    }

    public UserResponseDTO create(UserRequestDTO request) {
        if (profileRepository.findByUsernameAndActiveTrue(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Profile profile = new Profile();
        profile.setId(UUID.randomUUID());
        profile.setUsername(request.getUsername());
        profile.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        profile.setRole(request.getRole().toUpperCase());
        profile.setActive(true);
        profile.setCreatedAt(OffsetDateTime.now());

        Profile saved = profileRepository.save(profile);
        return toDTO(saved);
    }

    public UserResponseDTO update(UUID id, UserUpdateDTO request) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!profile.getActive()) {
            throw new RuntimeException("User not found");
        }

        if (request.getUsername() != null && !request.getUsername().equals(profile.getUsername())) {
            if (profileRepository.findByUsernameAndActiveTrue(request.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists");
            }
            profile.setUsername(request.getUsername());
        }

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            profile.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRole() != null) {
            profile.setRole(request.getRole().toUpperCase());
        }

        Profile saved = profileRepository.save(profile);
        return toDTO(saved);
    }

    public void delete(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setActive(false);
        profileRepository.save(profile);
    }

    private UserResponseDTO toDTO(Profile profile) {
        return new UserResponseDTO(
                profile.getId(),
                profile.getUsername(),
                profile.getRole(),
                profile.getCreatedAt()
        );
    }
}
