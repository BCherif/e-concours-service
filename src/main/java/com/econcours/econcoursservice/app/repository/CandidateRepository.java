package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends ECDefaultBaseRepository<Candidate> {
    Optional<Candidate> findByEmail(String mail);
}
