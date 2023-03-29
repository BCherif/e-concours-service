package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.entity.Notification;
import com.econcours.econcoursservice.app.repository.CandidateRepository;
import com.econcours.econcoursservice.app.repository.CompetitionRepository;
import com.econcours.econcoursservice.app.repository.NotificationRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.utils.Enumeration;
import com.econcours.econcoursservice.wrapper.NotificationWrapper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends ECDefaultBaseService<Notification, NotificationRepository> {
    private final CandidateRepository candidateRepository;
    private final CompetitionRepository competitionRepository;
    public NotificationService(NotificationRepository repository, ECEntityManager manager, ECLogger logger, CandidateRepository candidateRepository, CompetitionRepository competitionRepository) {
        super(repository, manager, logger);
        this.candidateRepository = candidateRepository;
        this.competitionRepository = competitionRepository;
    }

    public void createNotification(NotificationWrapper notificationWrapper) {
        try {
            Notification notification = new Notification();
            notification.setTitle(notificationWrapper.getTitle());
            notification.setDescription(notificationWrapper.getDescription());
            if (notificationWrapper.getCandidateUid() != null) {
                Candidate candidate = candidateRepository.getById(notificationWrapper.candidateUid);
                notification.setCandidate(candidate);
                notification.setType(Enumeration.NOTIFICATION_TYPE.Candidacy);
            }
            if (notificationWrapper.getCompetitionUid() != null) {
                Competition competition = competitionRepository.getById(notificationWrapper.getCompetitionUid());
                notification.setCompetition(competition);
                notification.setType(Enumeration.NOTIFICATION_TYPE.Competition);
            }

            repository.save(notification);

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
