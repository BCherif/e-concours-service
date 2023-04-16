package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.CompetitionResult;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionResultRepository extends ECDefaultBaseRepository<CompetitionResult> {
    Page<CompetitionResult> findAllByCandidacyCandidateUid(String candidacy_candidate_uid, Pageable pageable);
}
