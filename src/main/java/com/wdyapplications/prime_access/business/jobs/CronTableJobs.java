package com.wdyapplications.prime_access.business.jobs;

import com.wdyapplications.prime_access.utils.wdyemail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class CronTableJobs {
    private final EmailService emailService;
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Scheduled(cron = "0 0 20 * * SUN")
    public void envoyerRapportsHebdomadaires() {
        LocalDate finSemaine = LocalDate.now();                 // dimanche (jour d'exécution)
        LocalDate debutSemaine = finSemaine.with(DayOfWeek.MONDAY).minusWeeks(0); // lundi de la même semaine
        // Si l'exécution a lieu le dimanche ET qu'on veut couvrir lundi->samedi précédents :
        debutSemaine = finSemaine.minusDays(6);
        LocalDate finPeriode = finSemaine.minusDays(2); // vendredi

        log.info("Génération des rapports de présence du {} au {}", debutSemaine, finPeriode);

        emailService.envoyerRapportResponsable(debutSemaine, finPeriode, "zana.soro@orange.com");
        emailService.envoyerRapportsEmployes(debutSemaine, finPeriode);
    }

}
