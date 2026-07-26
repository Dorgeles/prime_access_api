package com.wdyapplications.prime_access.business.reports;


import com.wdyapplications.prime_access.dao.entity.Mouvement;
import com.wdyapplications.prime_access.dao.entity.Personnel;
import com.wdyapplications.prime_access.dao.repository.MouvementRepository;
import com.wdyapplications.prime_access.dao.repository.PersonnelRepository;
import com.wdyapplications.prime_access.utils.dto.reports.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportBusiness {
    final private PersonnelRepository personnelRepository;
    final private MouvementRepository mouvementRepository;

    public RapportResponsableDTO genererRapportResponsable(LocalDate debutSemaine, LocalDate finSemaine) {
        LocalDateTime debut = debutSemaine.atStartOfDay();
        LocalDateTime fin = finSemaine.atTime(LocalTime.MAX);

        List<Personnel> tousPersonnel = personnelRepository.findAll();
        List<Mouvement> mouvementsSemaine = mouvementRepository.findAllBetween(debut, fin);

        // Regroupement : personnelId -> (jour -> mouvements de ce jour)
        Map<Integer, Map<LocalDate, List<Mouvement>>> mouvementsParPersonnelEtJour = mouvementsSemaine.stream()
                .collect(Collectors.groupingBy(
                        m -> m.getPersonnel().getId(),
                        Collectors.groupingBy(m -> toLocalDate(m.getCreatedAt()))
                ));

        int nbJoursSemaine = (int) ChronoUnit.DAYS.between(debutSemaine, finSemaine) + 1;

        Map<java.time.DayOfWeek, StatistiqueJourDTO> statsParJour = new LinkedHashMap<>();
        int totalPresencesSemaine = 0;

        for (LocalDate jour = debutSemaine; !jour.isAfter(finSemaine); jour = jour.plusDays(1)) {
            LocalDate jourCourant = jour;
            long nbPresents = tousPersonnel.stream()
                    .filter(p -> mouvementsParPersonnelEtJour
                            .getOrDefault(p.getId(), Collections.emptyMap())
                            .containsKey(jourCourant))
                    .count();
            long nbAbsents = tousPersonnel.size() - nbPresents;

            statsParJour.put(jour.getDayOfWeek(), new StatistiqueJourDTO(jour, (int) nbPresents, (int) nbAbsents));
            totalPresencesSemaine += nbPresents;
        }

        // Liste des employés ayant au moins une absence dans la semaine
        List<PersonnelAbsenceDTO> absents = tousPersonnel.stream()
                .map(p -> {
                    Map<LocalDate, List<Mouvement>> joursPresents =
                            mouvementsParPersonnelEtJour.getOrDefault(p.getId(), Collections.emptyMap());
                    int nbJoursPresence = compterJoursPresents(debutSemaine, finSemaine, joursPresents);
                    int nbJoursAbsence = nbJoursSemaine - nbJoursPresence;
                    return new PersonnelAbsenceDTO( p.getId(), p.getPrenoms() + " " + p.getNom(), nbJoursAbsence);
                })
                .filter(dto -> dto.nbJoursAbsence() > 0)
                .sorted(Comparator.comparingInt(PersonnelAbsenceDTO::nbJoursAbsence).reversed())
                .toList();

        int totalPossible = tousPersonnel.size() * nbJoursSemaine;
        double tauxPresence = totalPossible == 0
                ? 0.0
                : Math.round((totalPresencesSemaine * 10000.0) / totalPossible) / 100.0;

        return new RapportResponsableDTO(
                debutSemaine,
                finSemaine,
                tousPersonnel.size(),
                statsParJour,
                tauxPresence,
                absents
        );
    }

    private int compterJoursPresents(LocalDate debut, LocalDate fin, Map<LocalDate, List<Mouvement>> joursPresents) {
        int count = 0;
        for (LocalDate jour = debut; !jour.isAfter(fin); jour = jour.plusDays(1)) {
            if (joursPresents.containsKey(jour)) count++;
        }
        return count;
    }

    // ------------------------------------------------------------------
    // RAPPORT INDIVIDUEL (un rapport par employé)
    // ------------------------------------------------------------------

    public RapportEmployeDTO genererRapportEmploye(Personnel personnel, LocalDate debutSemaine, LocalDate finSemaine) {
        LocalDateTime debut = debutSemaine.atStartOfDay();
        LocalDateTime fin = finSemaine.atTime(LocalTime.MAX);

        List<Mouvement> mouvements = mouvementRepository.findByPersonnelBetween(personnel.getId(), debut, fin);

        Map<LocalDate, List<Mouvement>> mouvementsParJour = mouvements.stream()
                .collect(Collectors.groupingBy(m -> toLocalDate(m.getCreatedAt())));

        List<JourPresenceDTO> details = new ArrayList<>();
        int nbPresences = 0;

        for (LocalDate jour = debutSemaine; !jour.isAfter(finSemaine); jour = jour.plusDays(1)) {
            List<Mouvement> mouvementsJour = mouvementsParJour.get(jour);
            boolean present = mouvementsJour != null && !mouvementsJour.isEmpty();

            LocalTime heureArrivee = present
                    ? mouvementsJour.stream()
                    .map(m -> toLocalTime(m.getCreatedAt()))
                    .min(LocalTime::compareTo)
                    .orElse(null)
                    : null;

            details.add(new JourPresenceDTO(jour, present, heureArrivee));
            if (present) nbPresences++;
        }

        int nbJoursSemaine = (int) ChronoUnit.DAYS.between(debutSemaine, finSemaine) + 1;

        return new RapportEmployeDTO(
                personnel,
                debutSemaine,
                finSemaine,
                details,
                nbPresences,
                nbJoursSemaine - nbPresences
        );
    }

    /**
     * Génère le rapport individuel pour tout le personnel (utile pour l'envoi en masse).
     */
    public List<RapportEmployeDTO> genererRapportsPourTous(LocalDate debutSemaine, LocalDate finSemaine) {
        return personnelRepository.findAll().stream()
                .map(p -> genererRapportEmploye(p, debutSemaine, finSemaine))
                .toList();
    }

    private LocalDate toLocalDate(java.util.Date date) {
        return date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private LocalTime toLocalTime(java.util.Date date) {
        return date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
    }

}
