package com.webApp14.UniHub.repository;

import com.webApp14.UniHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
