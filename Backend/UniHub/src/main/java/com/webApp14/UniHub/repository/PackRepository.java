package com.webApp14.UniHub.repository;

import com.webApp14.UniHub.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    @Query(value = "SELECT * FROM pack LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Pack> findLimitedPacks(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT * FROM pack WHERE i = :i LIMIT :initialLimit", nativeQuery = true)
    List<Pack> findAllByI(@Param("i") int i, @Param("initialLimit") int initialLimit);

}
