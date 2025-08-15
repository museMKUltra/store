package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.ProfileRepository;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John")
                .email("john@example.com")
                .password("password")
                .build();

        System.out.println(entityManager.contains(user) ? "Persistent" : "Transient / Detached"); // Transient / Detached

        userRepository.save(user);

        System.out.println(entityManager.contains(user) ? "Persistent" : "Transient / Detached"); // Persistent
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }
}
