package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends ECDefaultBaseRepository<Candidate> {
}
