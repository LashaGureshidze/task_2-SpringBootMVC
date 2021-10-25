package com.epam.task2.repository;

import com.epam.task2.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Set<Permission> findByUserId(Long userId);
}
