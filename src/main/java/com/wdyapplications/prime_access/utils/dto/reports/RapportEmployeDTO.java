package com.wdyapplications.prime_access.utils.dto.reports;

import com.wdyapplications.prime_access.dao.entity.Personnel;

import java.time.LocalDate;
import java.util.List;

/**
 * Rapport individuel destiné à un employé.
 */
public record RapportEmployeDTO(
        Personnel personnel,
        LocalDate debutSemaine,
        LocalDate finSemaine,
        List<JourPresenceDTO> details,
        int nbJoursPresence,
        int nbJoursAbsence
) {}
