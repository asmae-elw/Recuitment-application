package com.infosat.appRecruitment.repository;

import com.infosat.appRecruitment.model.ERole;
import com.infosat.appRecruitment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}