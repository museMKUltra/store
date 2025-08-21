package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.projections.UserSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query("select p.id as id, p.user.email as email from Profile p where p.loyaltyPoints > :points order by p.user.email")
    @EntityGraph(attributePaths = "user")
    List<UserSummary> findLoyalProfiles(@Param("points") Integer points);
}
