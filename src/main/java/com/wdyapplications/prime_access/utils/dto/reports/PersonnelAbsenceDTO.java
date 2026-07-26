package com.wdyapplications.prime_access.utils.dto.reports;

/**
 * Ligne récapitulative pour un employé absent au moins un jour dans la semaine.
 */
public record PersonnelAbsenceDTO(
        Integer personnelId,
        String nomComplet,
        int nbJoursAbsence
) {}