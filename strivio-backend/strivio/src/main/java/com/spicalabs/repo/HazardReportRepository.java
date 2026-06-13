package com.spicalabs.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spicalabs.strivio.model.HazardReport;

public interface HazardReportRepository extends JpaRepository<HazardReport, UUID> {

}
