package com.wdyapplications.prime_access.business.reports;

import com.wdyapplications.prime_access.dao.entity.Mouvement;
import com.wdyapplications.prime_access.dao.entity.Personnel;
import com.wdyapplications.prime_access.dao.repository.MouvementRepository;
import com.wdyapplications.prime_access.dao.repository.PersonnelRepository;
import com.wdyapplications.prime_access.utils.dto.reports.RapportEmployeDTO;
import com.wdyapplications.prime_access.utils.dto.reports.RapportResponsableDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportBusinessTest {

    @Mock
    private PersonnelRepository personnelRepository;

    @Mock
    private MouvementRepository mouvementRepository;

    @InjectMocks
    private ReportBusiness reportBusiness;

    @Test
    void genererRapportResponsable_neCompteQueLundiAVendredi() {
        LocalDate debut = LocalDate.of(2026, 9, 1);
        LocalDate fin = LocalDate.of(2026, 9, 7);

        Personnel personnel = personnel(1, "Awa", "Traore");
        when(personnelRepository.findAll()).thenReturn(List.of(personnel));
        when(mouvementRepository.findAllBetween(
                java.time.LocalDateTime.of(2026, 9, 1, 0, 0),
                java.time.LocalDateTime.of(2026, 9, 8, 0, 0)
        )).thenReturn(List.of(
                mouvement(1, personnel, LocalDate.of(2026, 9, 1)),
                mouvement(2, personnel, LocalDate.of(2026, 9, 5)),
                mouvement(3, personnel, LocalDate.of(2026, 9, 6))
        ));

        RapportResponsableDTO rapport = reportBusiness.genererRapportResponsable(debut, fin);

        assertThat(rapport.finSemaine()).isEqualTo(LocalDate.of(2026, 9, 7));
        assertThat(rapport.statsParJour()).hasSize(5);
        assertThat(rapport.tauxPresence()).isEqualTo(14.29);
        assertThat(rapport.absents()).hasSize(1);
        assertThat(rapport.absents().get(0).nbJoursAbsence()).isEqualTo(6);
    }

    @Test
    void genererRapportEmploye_ignoreLeWeekendDansLeTotal() {
        Personnel personnel = personnel(1, "Awa", "Traore");
        when(mouvementRepository.findByPersonnelBetween(
                1,
                java.time.LocalDateTime.of(2026, 9, 1, 0, 0),
                java.time.LocalDateTime.of(2026, 9, 5, 23, 59, 59, 999_999_999)
        )).thenReturn(List.of(
                mouvement(1, personnel, LocalDate.of(2026, 9, 1)),
                mouvement(2, personnel, LocalDate.of(2026, 9, 5))
        ));

        RapportEmployeDTO rapport = reportBusiness.genererRapportEmploye(personnel, LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 5));

        assertThat(rapport.nbJoursPresence()).isEqualTo(2);
        assertThat(rapport.nbJoursAbsence()).isEqualTo(3);
        assertThat(rapport.details()).hasSize(5);
    }

    private Personnel personnel(int id, String prenoms, String nom) {
        Personnel personnel = new Personnel();
        personnel.setId(id);
        personnel.setPrenoms(prenoms);
        personnel.setNom(nom);
        return personnel;
    }

    private Mouvement mouvement(int id, Personnel personnel, LocalDate date) {
        Mouvement mouvement = new Mouvement();
        mouvement.setId(id);
        mouvement.setPersonnel(personnel);
        mouvement.setCreatedAt(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return mouvement;
    }
}
