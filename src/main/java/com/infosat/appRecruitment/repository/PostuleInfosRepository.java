package com.infosat.appRecruitment.repository;


import com.infosat.appRecruitment.model.PostuleInfos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostuleInfosRepository extends JpaRepository<PostuleInfos, Long> {

    Optional<PostuleInfos> findPostuleInfosById(Long id);
}



