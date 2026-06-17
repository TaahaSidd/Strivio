package com.spicalabs.strivio.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spicalabs.strivio.model.Path;

public interface PathRepo extends JpaRepository<Path, UUID> {
    
}
