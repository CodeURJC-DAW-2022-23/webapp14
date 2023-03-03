package com.webApp14.UniHub.repository;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PackRepository extends JpaRepository<Pack, Long> {
    Optional<Pack> findByPackDescription_longContaining(String keyword);
}
