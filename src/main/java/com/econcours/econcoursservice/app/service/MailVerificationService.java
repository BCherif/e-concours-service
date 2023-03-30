package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.MailVerification;
import com.econcours.econcoursservice.app.repository.MailVerificationRepository;
import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.wrapper.CandidateWithToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailVerificationService extends ECDefaultBaseService<MailVerification, MailVerificationRepository> {
    private final MailVerificationRepository mailVerificationRepository;
    private final SendEmailService emailService;
    private final UserRepository userRepository;
    private final CandidateService candidateService;
    public MailVerificationService(MailVerificationRepository repository, ECEntityManager manager, ECLogger logger, MailVerificationRepository mailVerificationRepository, SendEmailService emailService, UserRepository userRepository, CandidateService candidateService) {
        super(repository, manager, logger);
        this.mailVerificationRepository = mailVerificationRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.candidateService = candidateService;
    }

    public ECResponse<?> checkEmail(MailVerification mailVerification) {
        try {
            Optional<MailVerification> mailVerificationOptional = mailVerificationRepository.findByConfirmationCodeAndMail(mailVerification.getConfirmationCode(), mailVerification.getMail());
            if (mailVerificationOptional.isPresent()) {
                // CODE CORRECT
                Optional<User> userOptional = userRepository.getByUsername(mailVerification.getMail());
                if (userOptional.isPresent()) {
                    // OLD USER
                    CandidateWithToken candidateWithToken = candidateService.signInCandidate(mailVerification.getMail());
                    candidateWithToken.setUid("1");
                    return ECResponse.success(candidateWithToken, "Connecté avec succès");
                } else {
                    // NEW USER
                    mailVerification.setUid("0");
                    return ECResponse.success(mailVerification, "Code correct");
                }
            } else {
                return ECResponse.error("Ce code n'est pas correct");
            }
        } catch (Exception e) {
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

    public ECResponse<?> sendVerificationCode(String mail) {
        try {
            String verificationCode = makeVerification(6);
            String message = mailTemplate(verificationCode);
            String objct = "CONNEXION À E-CONCOURS";
            Optional<MailVerification> mailVerificationOptional = mailVerificationRepository.findByMail(mail);
            if (mailVerificationOptional.isPresent()) {
                MailVerification mailVerificationLast = mailVerificationOptional.get();
                mailVerificationLast.setConfirmationCode(verificationCode);
                mailVerificationRepository.save(mailVerificationLast);
                emailService.sendEmail(mail, message, objct);
                return ECResponse.success(mailVerificationLast, "Code envoyé par mail");
            }

            MailVerification mailVerification = MailVerification.builder()
                    .confirmationCode(verificationCode)
                    .mail(mail)
                    .build();
           MailVerification mailVerificationSaved = mailVerificationRepository.save(mailVerification);
            emailService.sendEmail(mail, message, objct);
            return ECResponse.success(mailVerificationSaved, "Code correct");
        } catch (Exception e) {
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

    public String makeVerification(int qoutas) {
        String passArray = "0123456789";
        int charactersLength = passArray.length();
        String result = "";
        for (int i = 0; i < qoutas; i++) {
            result += passArray.charAt((int) Math.floor(Math.random() * charactersLength));
        }
        return result;
    }

    String mailTemplate(String verificationCode) {
        return "<div style=\"border: 1px grey solid; border-radius: 10px; padding: 20px; text-align: center\">\n" +
                "  <h1 style=\"color: #5e9ca0;'\">CONNEXION À E-CONCOURS</h1>\n" +
                " <img style=\"height: 100px;\" class=\"welcomeImg\" src=\"https://firebasestorage.googleapis.com/v0/b/e-concours.appspot.com/o/15820150.jpg?alt=media&token=578e3e06-044b-42b0-b52f-531bbf448172\" alt=\"\">" +
                "  <div style=\"border-bottom: 1px grey solid\">\n" +
                "    \n" +
                "  </div>\n" +
                "\n" +
                "<p>Votre code de vérification pour l'application E-Concours est le suivant:</p>\n" +
                "<h2 style=\"color: #2e6c80;\">"+verificationCode+"</h2>\n" +
                "<p>Ce code est utilisable une seule fois.  Veuillez à ne pas le partager avec quiconque.</p>\n" +
                "<h2 style=\"color: #2e6c80;\">Quelques fonctionnalités de l'application:</h2>\n" +
                "<ol style=\"list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;\">\n" +
                "<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://html-online.com/img/01-interactive-connection.png\" alt=\"interactive connection\" width=\"45\" />Candidature aux concours nationaux</li>\n" +
                "<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://html-online.com/img/02-html-clean.png\" alt=\"html cleaner\" width=\"45\" /> Reception des résultats par email et par notification du téléphone</li>\n" +
                "<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://html-online.com/img/03-docs-to-html.png\" alt=\"Word to html\" width=\"45\" /> Paiement des frais de concours par Orange money et Moov Money</li>\n" +
                "</ol>\n" +
                "</div>";
    }
}
