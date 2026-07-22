

package com.wdyapplications.prime_access.dao.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.wdyapplications.prime_access.dao.entity.Mouvement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wdyapplications.prime_access.utils.*;
import com.wdyapplications.prime_access.utils.dto.*;
import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.contract.Request;
import com.wdyapplications.prime_access.utils.contract.Response;
import com.wdyapplications.prime_access.dao.repository.base._MouvementRepository;

/**
 * Repository : Mouvement.
 *
 * @author dorgeddy
 */
@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Integer>, _MouvementRepository {
    @Query("SELECT m FROM Mouvement m " +
            "WHERE m.personnel.id = :idPersonnel " +
            "AND FUNCTION('DATE', m.createdAt) = CURRENT_DATE " +
            "ORDER BY m.createdAt DESC LIMIT 1")
    Optional<Mouvement> findDernierMouvement(@Param("idPersonnel") Integer idPersonnel);

    @Query(value =
            "SELECT date_trunc(:unite, m.created_at) AS periode, " +
                    "       m.type_mouvement AS type, " +
                    "       COUNT(*) AS total " +
                    "FROM mouvement m " +
                    "WHERE m.created_at BETWEEN :debut AND :fin " +
                    "GROUP BY periode, m.type_mouvement " +
                    "ORDER BY periode ASC",
            nativeQuery = true)
    List<Object[]> statistiquesBrutes(
            @Param("unite") String unite,
            @Param("debut") LocalDateTime debut,
            @Param("fin") LocalDateTime fin
    );
}
