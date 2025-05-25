package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.EmailFailureException;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.entity.user.VerificationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * Klasa serwisu odpowiedzialna za wysyłanie wiadomości e-mail.
 * Obsługuje wysyłanie e-maili weryfikacyjnych oraz wiadomości
 * do resetowania hasła.
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
     * Adres URL aplikacji klienckiej.
     */
    @Value("${frontend.url}")
    private String frontendUrl;

    /**
     * Obiekt służący do wysyłania maili
     */
    private final JavaMailSender mailSender;

    /**
     * Konstruktor klasy serwisu wiadomości email. Wstrzykuje niezbędne zależności.
     *
     * @param mailSender     obiekt służący do wysyłania wiadomości
     * @param templateEngine silnik szablonów Thymeleaf do generowania zawartości email na podstawie plików HTML
     */
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * Metoda przetwarzająca wskazany plik HTML i wysyłająca wiadomość email do weryfikacji adresu użytkownika.
     *
     * @param token Token weryfikacyjny JWT
     * @throws EmailFailureException wyrzucane w przypadku błędu wysyłania wiadomości
     */
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
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw new EmailFailureException("Email could not be send");
        }
    }

    /**
     * Metoda przetwarzająca wskazany plik HTML i wysyłająca wiadomość email do resetowania hasła użytkownika.
     *
     * @param token Token weryfikacyjny JWT
     * @throws EmailFailureException wyrzucane w przypadku błędu wysyłania wiadomości
     */
    public void sendPasswordResetEmail(User user, String token) throws EmailFailureException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            context.setVariable("url", frontendUrl + UrlPath.RESET + "?token=" + token);
            String verificationEmailTemplate = templateEngine.process("reset_password_email", context);
            helper.setFrom(pcForgeEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Reset hasła");
            helper.setText(verificationEmailTemplate, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw new EmailFailureException("Email could not be send");
        }
    }
}
