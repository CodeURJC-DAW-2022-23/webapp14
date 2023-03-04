package com.webApp14.UniHub.repository;

import com.webApp14.UniHub.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findBypackDescriptionLongContaining(String keyword);

    @Query("SELECT p FROM Pack p JOIN p.users u WHERE u.id = :userId")
    List<Pack> findPacksByUser(@Param("userId") Long userId);
}
