package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.EmailFailureException;
import com.pc_forge.backend.model.entity.user.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${email.from}")
    private String pcForgeEmail;

    @Value("${frontend.url}")
    private String frontendUrl;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(pcForgeEmail);
        return message;
    }

    public void sendVerificationEmail(VerificationToken token) throws EmailFailureException {
        SimpleMailMessage message = getSimpleMailMessage();
        message.setTo(token.getUser().getEmail());
        message.setSubject("Weryfikacja adresu");
        message.setText("Kliknij w link poniżej aby potwierdzić swój adres email: \n"
                + frontendUrl + "/verify?token=" + token.getToken());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
            throw new EmailFailureException("Email could not be send");
        }
    }
}
