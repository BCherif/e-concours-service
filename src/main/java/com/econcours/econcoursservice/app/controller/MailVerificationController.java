package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.MailVerification;
import com.econcours.econcoursservice.app.service.MailVerificationService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.wrapper.CandidateSaveEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mailVerifications")
public class MailVerificationController extends ECDefaultBaseController<MailVerification, MailVerificationService> {
    private final MailVerificationService mailVerificationService;
    public MailVerificationController(MailVerificationService service, MailVerificationService mailVerificationService) {
        super(service);
        this.mailVerificationService = mailVerificationService;
    }

    @PostMapping("/check_mail")
    public ECResponse<?> checkEmail(@RequestBody MailVerification mailVerification)  {
        return mailVerificationService.checkEmail(mailVerification);
    }

    @GetMapping("/send_verification_code/{mail}")
    public ECResponse<?> sendVerificationCode(@PathVariable String mail) {
        return mailVerificationService.sendVerificationCode(mail);
    }
}
