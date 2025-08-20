package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @EntityGraph(attributePaths = "user")
    @Query("select p from Profile p left join User u on p.id = u.id where p.loyaltyPoints > :points order by u.email")
    List<Profile> findByLoyaltyPoints(@Param("points") Integer points);
}
