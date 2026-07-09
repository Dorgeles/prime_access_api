package com.wdyapplications.prime_access.utils.dto;

import java.time.LocalDateTime;

public class StatistiqueMouvementDTO {

    private final LocalDateTime periode;
    private final String typeMouvement;
    private final long total;

    public StatistiqueMouvementDTO(LocalDateTime periode, String typeMouvement, long total) {
        this.periode = periode;
        this.typeMouvement = typeMouvement;
        this.total = total;
    }

    public LocalDateTime getPeriode() {
        return periode;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "StatistiqueMouvementDTO{" +
                "periode=" + periode +
                ", typeMouvement='" + typeMouvement + '\'' +
                ", total=" + total +
                '}';
    }
}

