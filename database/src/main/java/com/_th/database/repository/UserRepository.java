package com._th.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com._th.database.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
