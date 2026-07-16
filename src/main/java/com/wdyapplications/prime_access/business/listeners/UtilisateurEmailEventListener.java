package com.wdyapplications.prime_access.business.listeners;

import com.wdyapplications.prime_access.business.publisher.UtilisateurEmailEventPublisher;
import com.wdyapplications.prime_access.dao.entity.Utilisateur;
import com.wdyapplications.prime_access.utils.events.UtilisateurEmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UtilisateurEmailEventListener {
    @Autowired
    private UtilisateurEmailEventPublisher utilisateurEmailEventPublisher;

    @EventListener
    public void onServiceOrderStateChanged(UtilisateurEmailNotification event) {
        Utilisateur user = event.getUtilisateur();
        Map<String, Object> data = event.getData();

        utilisateurEmailEventPublisher.publishEmailEvent(user, data);
    }
}
