package com.wdyapplications.prime_access.utils.dto.reports;

import java.time.LocalDate;

/**
 * Statistiques agrégées (présents/absents) pour un jour donné, tous employés confondus.
 */
public record StatistiqueJourDTO(
        LocalDate date,
        int nbPresents,
        int nbAbsents
) {}
