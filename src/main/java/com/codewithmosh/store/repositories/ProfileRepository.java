package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.projections.UserSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @EntityGraph(attributePaths = "user")
    @Query("select u.id as id, u.email as email from Profile p left join User u on p.id = u.id where p.loyaltyPoints > :points order by u.email")
    List<UserSummary> findByLoyaltyPoints(@Param("points") Integer points);
}
