package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.Address;
import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.AddressRepository;
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
    private final AddressRepository addressRepository;

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

    @Transactional
    public void showRelatedAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getUser().getEmail());
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John")
                .email("john@example.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
        System.out.println(entityManager.contains(user) ? "Persistent" : "Transient / Detached");
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(8L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("john.doe@example.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    public void populateUsers() {
        var user = User.builder()
                .name("Twenty")
                .email("twenty@example.com")
                .password("password")
                .build();

        var profile = new Profile(user);
        profile.setLoyaltyPoints(20);

        profileRepository.save(profile);
    }

    @Transactional
    public void findLoyaltyPoints() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(u -> {
            System.out.println(u.getId() + ": " + u.getEmail());
        });
    }
}
