package com.wdyapplications.prime_access.utils.events;


import com.wdyapplications.prime_access.dao.entity.Utilisateur;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class UtilisateurEmailNotification {
    private final Utilisateur utilisateur;
    private final Map<String, Object> data;
    private final LocalDateTime occurredAt;
    public UtilisateurEmailNotification(Utilisateur utilisateur, Map<String, Object> data) {
        this.utilisateur = utilisateur;
        this.data = data;
        this.occurredAt = LocalDateTime.now();
    }
}
