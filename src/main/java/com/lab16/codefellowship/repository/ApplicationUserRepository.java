package com.lab16.codefellowship.repository;

import com.lab16.codefellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
     ApplicationUser findApplicationUserByUsername(String username);

}
