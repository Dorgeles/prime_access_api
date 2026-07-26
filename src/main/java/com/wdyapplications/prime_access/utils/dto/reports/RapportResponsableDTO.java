package com.wdyapplications.prime_access.utils.dto.reports;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Rapport global destiné au responsable.
 */
public record RapportResponsableDTO(
        LocalDate debutSemaine,
        LocalDate finSemaine,
        int totalPersonnel,
        Map<DayOfWeek, StatistiqueJourDTO> statsParJour,
        double tauxPresence,
        List<PersonnelAbsenceDTO> absents
) {}