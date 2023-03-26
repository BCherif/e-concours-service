package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.MailVerification;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;

import java.util.Optional;

public interface MailVerificationRepository extends ECDefaultBaseRepository<MailVerification> {
    Optional<MailVerification> findByConfirmationCodeAndMail(String confirmationCode, String mail);
    Optional<MailVerification> findByMail(String mail);
}
