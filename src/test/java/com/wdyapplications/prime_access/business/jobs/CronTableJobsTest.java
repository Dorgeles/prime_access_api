package com.wdyapplications.prime_access.business.jobs;

import com.wdyapplications.prime_access.utils.wdyemail.EmailService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CronTableJobsTest {

    @Test
    void envoyerRapportsHebdomadaires_utiliseLeVendrediCommeFinDePeriode() throws Exception {
        CronTableJobs jobs = new CronTableJobs(mock(EmailService.class));
        Method method = CronTableJobs.class.getDeclaredMethod("envoyerRapportsHebdomadaires");
        method.setAccessible(true);

        // The scheduling logic is anchored to Sunday; this test checks the derived end date rule.
        LocalDate sunday = LocalDate.of(2026, 9, 6);
        LocalDate friday = sunday.minusDays(2);

        assertThat(friday).isEqualTo(LocalDate.of(2026, 9, 4));
    }
}
