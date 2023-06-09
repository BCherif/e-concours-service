package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import com.econcours.econcoursservice.utils.Enumeration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CandidacyRepository extends ECDefaultBaseRepository<Candidacy> {
    Optional<Candidacy> findByCandidateUidAndCompetitionUid(String candidate_uid, String competition_uid);
    Page<Candidacy> findAllByCandidateUid(String candidate_uid, Pageable pageable);

    Page<Candidacy> findAllByCompetitionUidAndState(String competition_uid, Enumeration.STATE_CANDIDACY state, Pageable pageable);
}
