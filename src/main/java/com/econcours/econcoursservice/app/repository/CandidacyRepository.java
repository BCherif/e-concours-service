package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;

import java.util.Optional;

public interface CandidacyRepository extends ECDefaultBaseRepository<Candidacy> {
    Optional<Candidacy> findByCandidateUid(String candidateUid);
}
