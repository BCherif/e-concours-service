package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.entity.Notification;
import com.econcours.econcoursservice.app.repository.CandidacyRepository;
import com.econcours.econcoursservice.app.repository.CandidateRepository;
import com.econcours.econcoursservice.app.repository.CompetitionRepository;
import com.econcours.econcoursservice.app.repository.NotificationRepository;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.utils.Enumeration;
import com.econcours.econcoursservice.wrapper.NotificationWrapper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends ECDefaultBaseService<Notification, NotificationRepository> {
    private final CandidacyRepository candidacyRepository;
    private final CompetitionRepository competitionRepository;
    private final SendEmailService emailService;
    public NotificationService(NotificationRepository repository, ECEntityManager manager, ECLogger logger, CandidacyRepository candidacyRepository, CompetitionRepository competitionRepository, SendEmailService emailService) {
        super(repository, manager, logger);
        this.candidacyRepository = candidacyRepository;
        this.competitionRepository = competitionRepository;
        this.emailService = emailService;
    }

    public void createNotification(NotificationWrapper notificationWrapper) {
        try {
            Notification notification = new Notification();
            notification.setTitle(notificationWrapper.getTitle());
            notification.setDescription(notificationWrapper.getDescription());
            if (notificationWrapper.getCandidacyUid() != null) {
                Candidacy candidacy = candidacyRepository.getById(notificationWrapper.getCandidacyUid());
                notification.setCandidacy(candidacy);
                notification.setType(Enumeration.NOTIFICATION_TYPE.Candidacy);
                emailService.sendEmail(candidacy.getCandidate().getEmail(), setMailContentCandidacy(candidacy.getCompetition().getTitle(), notificationWrapper.getDescription()), "CANDIDATURE AU CONCOURS " + candidacy.getCompetition().getTitle());
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

    String setMailContentCandidacy(String competitionTitle, String description) {
        return "<div style=\"border: 1px grey solid; border-radius: 10px; padding: 15px; width: 600px;\">\n" +
                "<div style=\"text-align: center;\"><img style=\"height: 100px;\" src=\"https://firebasestorage.googleapis.com/v0/b/e-concours.appspot.com/o/15820150.jpg?alt=media&amp;token=578e3e06-044b-42b0-b52f-531bbf448172\" /></div>\n" +
                "<h1 style=\"color: #4485b8; text-align: center; margin-bottom: 40px;\">Candididature au concours <span style=\"background-color: #4485b8; color: #ffffff; padding: 0 5px;\">"+competitionTitle+"</span></h1>\n" +
                "<h4 style=\"line-height: 1.6;\">"+description+"</h4>\n" +
                "</div>";
    }
}
