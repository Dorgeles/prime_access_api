package com.wdyapplications.prime_access.utils.wdyemail;

import com.wdyapplications.prime_access.business.reports.ReportBusiness;
import com.wdyapplications.prime_access.dao.entity.Personnel;
import com.wdyapplications.prime_access.utils.dto.reports.RapportEmployeDTO;
import com.wdyapplications.prime_access.utils.dto.reports.RapportResponsableDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private ReportBusiness reportBusiness;
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
    private void envoyerMail(String to, String sujet, String contenuHtml) throws MessagingException {
        try {
            MimeMessage message = new MimeMessage(emailConfig.getSession());
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setFrom("noreply@wdyapplications.com", "Prime Access");
            helper.setSubject(sujet);
            helper.setText(contenuHtml, true);
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void envoyerRapportResponsable(LocalDate debut, LocalDate fin, String emailResponsable) {
        try {
            RapportResponsableDTO rapport = reportBusiness.genererRapportResponsable(debut, fin);

            Context context = new Context(Locale.FRENCH);
            context.setVariable("nomEntreprise", "Prime Access");
            context.setVariable("dateDebut", debut.format(FORMAT_DATE));
            context.setVariable("dateFin", fin.format(FORMAT_DATE));
            context.setVariable("rapport", rapport);

            String contenuHtml = templateEngine.process("weekly-rapport-admin", context);

            envoyerMail(emailResponsable, "Rapport hebdomadaire de présence - " + "Prime Access", contenuHtml);
            log.info("Rapport responsable envoyé à {}", emailResponsable);

        } catch (Exception e) {
            log.error("Erreur lors de l'envoi du rapport responsable", e);
        }
    }


    // ------------------------------------------------------------------
    // Rapports individuels
    // ------------------------------------------------------------------

    public void envoyerRapportsEmployes(LocalDate debut, LocalDate fin) {
        List<RapportEmployeDTO> rapports = reportBusiness.genererRapportsPourTous(debut, fin);

        for (RapportEmployeDTO rapport : rapports) {
            Personnel personnel = rapport.personnel();

            if (personnel.getEmail() == null || personnel.getEmail().isBlank()) {
                log.warn("Personnel {} sans email, rapport non envoyé", personnel.getId());
                continue;
            }

            try {
                Context context = new Context(Locale.FRENCH);
                context.setVariable("nomEntreprise", "Prime Access");
                context.setVariable("dateDebut", debut.format(FORMAT_DATE));
                context.setVariable("dateFin", fin.format(FORMAT_DATE));
                context.setVariable("rapport", rapport);
                context.setVariable("prenom", personnel.getPrenoms());

                String contenuHtml = templateEngine.process("weekly-report-employe", context);

                envoyerMail(personnel.getEmail(), "Votre récapitulatif hebdomadaire - " + "Prime Access" , contenuHtml);
                log.info("Rapport envoyé à {}", personnel.getEmail());

            } catch (Exception e) {
                log.error("Erreur lors de l'envoi du rapport à {}", personnel.getEmail(), e);
            }
        }
    }

}
