package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Notification;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import com.econcours.econcoursservice.utils.Enumeration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotificationRepository extends ECDefaultBaseRepository<Notification> {
    Page<Notification> findAllByCandidacyCandidateUid(String candidacy_candidate_uid, Pageable pageable);

    Integer countByCandidacyCandidateUidAndReading(String candidacy_candidate_uid, Enumeration.NOTIFICATION_READING reading);
}
