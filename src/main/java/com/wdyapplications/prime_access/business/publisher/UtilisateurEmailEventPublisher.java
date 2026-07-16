package com.wdyapplications.prime_access.business.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdyapplications.prime_access.dao.entity.Utilisateur;
import com.wdyapplications.prime_access.utils.wdyemail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UtilisateurEmailEventPublisher {
    @Autowired
    private EmailService emailService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void publishEmailEvent(Utilisateur utilisateur, Map<String, Object> emailData) {
        try {
            // i need to look throught emailDate template entry, and send email with the corresponding template and data
            String template = (String) emailData.get("template");
            if (template == null) {
                throw new IllegalArgumentException("Email template is missing in the event data.");
            }
            // use a switch case to trigger the right emailservice methode to call
            switch (template) {
                case "welcome-email":
                    emailService.sendWelcomeEmail(utilisateur.getPersonnel().getEmail(), utilisateur.getPersonnel().getNom() +" " + utilisateur.getPersonnel().getPrenoms());
                    break;
                case "login-notification":
                    emailService.sendLoginNotification(utilisateur.getPersonnel().getEmail(), utilisateur.getPersonnel().getNom() +" " + utilisateur.getPersonnel().getPrenoms(),
                            (String) emailData.get("device"),
                            (String) emailData.get("location"),
                            (String) emailData.get("time"));
                    break;
                case "prescription-ready-email":
                    emailService.sendPrescriptionReadyNotification(utilisateur.getPersonnel().getEmail(), utilisateur.getPersonnel().getNom() +" " + utilisateur.getPersonnel().getPrenoms(),
                            (String) emailData.get("pharmacyName"),
                            (String) emailData.get("pharmacyAddress"),
                            (String) emailData.get("pharmacyPhone"),
                            (String) emailData.get("pharmacyHours"),
                            (String) emailData.get("orderReference"),
                            (String) emailData.get("pharmacyMapLink"));
                    break;
                case "password-reset":
                    emailService.sendPasswordReset(utilisateur.getPersonnel().getEmail(), utilisateur.getPersonnel().getNom() +" " + utilisateur.getPersonnel().getPrenoms(),
                            (String) emailData.get("resetLink"));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown email template: " + template);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
