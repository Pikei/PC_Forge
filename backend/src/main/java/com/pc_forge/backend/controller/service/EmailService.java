package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.EmailFailureException;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.entity.user.VerificationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * Klasa serwisu email. Służy do wysyłania maili z linkiem weryfikacyjnym do
 */
@Service
public class EmailService {
    private final SpringTemplateEngine templateEngine;
    /**
     * Adres email, z którego będą wysyłane wiadomości
     */
    @Value("${email.from}")
    private String pcForgeEmail;

    /**
     *
     */
    @Value("${frontend.url}")
    private String frontendUrl;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    private SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(pcForgeEmail);
        return message;
    }

    public void sendVerificationEmail(VerificationToken token) throws EmailFailureException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            context.setVariable("url", frontendUrl + UrlPath.VERIFY + "?token=" + token.getToken());
            String verificationEmailTemplate = templateEngine.process("verification_email", context);
            helper.setFrom(pcForgeEmail);
            helper.setTo(token.getUser().getEmail());
            helper.setSubject("Weryfikacja adresu");
            helper.setText(verificationEmailTemplate, true);
            mailSender.send(message);
        } catch (MailException | MessagingException e) {
            System.out.println(e.getMessage());
            throw new EmailFailureException("Email could not be send");
        }
    }

    public void sendPasswordResetEmail(User user, String token) throws EmailFailureException {
        SimpleMailMessage message = getSimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reset hasła");
        message.setText("Kliknij w link poniżej aby zresetować swoje hasło: \n"
                + frontendUrl + UrlPath.PASSWORD_RESET + "?" + RequestParams.TOKEN + "=" + token);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
            throw new EmailFailureException("Email could not be send");
        }
    }
}
