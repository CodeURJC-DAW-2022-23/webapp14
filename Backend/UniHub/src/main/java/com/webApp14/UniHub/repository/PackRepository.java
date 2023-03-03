package com.webApp14.UniHub.repository;

import com.webApp14.UniHub.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findBypackDescriptionLongContaining(String keyword);
}
