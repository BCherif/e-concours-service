package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
