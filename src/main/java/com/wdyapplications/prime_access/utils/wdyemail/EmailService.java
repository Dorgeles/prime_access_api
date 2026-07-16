package com.wdyapplications.prime_access.utils.wdyemail;

import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendWelcomeEmail(String to, String name) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("company", "Prime Access");
        variables.put("year", LocalDate.now().getYear());

        sendEmail(to, "Welcome to Our Platform!", "welcome-email", variables);
    }

    public void sendLoginNotification(String to, String name, String device, String location, String time) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("device", device);
        variables.put("location", location);
        variables.put("time", time);
        variables.put("company", "Prime Access");
        variables.put("year", LocalDate.now().getYear());

        sendEmail(to, "New Login to Your Account", "login-notification", variables);
    }

    public void sendPrescriptionReadyNotification(String to, String name, String pharmacyName, String pharmacyAddress, String pharmacyPhone, String pharmacyHours, String orderReference, String pharmacyMapLink) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("pharmacyName", pharmacyName);
        variables.put("pharmacyAddress", pharmacyAddress);
        variables.put("pharmacyPhone", pharmacyPhone);
        variables.put("pharmacyHours", pharmacyHours);
        variables.put("orderReference", orderReference);
        variables.put("pharmacyMapLink", pharmacyMapLink);
        variables.put("company", "Prime Access");
        variables.put("year", LocalDate.now().getYear());

        sendEmail(to, "Votre Prescription est prête!", "prescription-ready-email", variables);
    }

    public void sendPasswordReset(String to, String name, String resetLink) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("resetLink", resetLink);
        variables.put("company", "Prime Access");
        variables.put("year", LocalDate.now().getYear());

        sendEmail(to, "Reset Your Password", "password-reset", variables);
    }


    private void sendEmail(String to, String subject, String template, Map<String, Object> variables) {
        try {
            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process(template, context);

            MimeMessage message = new MimeMessage(emailConfig.getSession());
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom("noreply@wdyapplications.com", "Prime Access");


            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
