package com.wdyapplications.prime_access.utils.dto.reports;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Statut de présence d'un employé pour un jour donné.
 */
public record JourPresenceDTO(
        LocalDate date,
        boolean present,
        LocalTime heureArrivee
) {}