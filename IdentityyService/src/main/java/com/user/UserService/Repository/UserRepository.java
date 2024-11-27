package com.user.UserService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.user.UserService.Entity.Role;
import com.user.UserService.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    Role findUserRoleByUsername(@Param("username") String username);
}
