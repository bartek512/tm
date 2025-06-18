package com.bw.task_manager.repository;

import com.bw.task_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLogin(String login);


    @Query("SELECT u FROM User u WHERE u.login =:identifier or u.email =:identifier")
    Optional<User> findByEmailOrLogin(@Param("identifier") String identifier);
}

