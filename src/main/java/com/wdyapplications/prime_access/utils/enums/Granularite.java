package com.wdyapplications.prime_access.utils.enums;

public enum Granularite {

    HEURE("hour"),
    JOUR("day"),
    SEMAINE("week"),
    MOIS("month"),
    ANNEE("year");

    private final String unitePostgres;

    Granularite(String unitePostgres) {
        this.unitePostgres = unitePostgres;
    }

    public String getUnitePostgres() {
        return unitePostgres;
    }
}
