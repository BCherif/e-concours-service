package com.econcours.econcoursservice.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String mail, String message, String subject) {
        try {
            System.out.println("start sending...");
            MimeMessage msg = javaMailSender.createMimeMessage();

            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//            helper.setTo(applicationUser.getUsername());
            helper.setTo(new String[]{mail});
//            helper.setTo(new String[]{applicationUser.getUsername()});

            helper.setSubject(subject);
//            helper.setSubject("Inscription à l'administration de FCS.");

            // default = text/plain
            //helper.setText("Check attachment for image!");

            helper.setText(message, true);
            javaMailSender.send(msg);
            System.out.println("end sending...");

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


}
