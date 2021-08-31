package com.infosat.appRecruitment.repository;

import com.infosat.appRecruitment.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    void deleteOffreById(Long id);

    Optional<Offre> findOffreById(Long id);
}
