
package com.wdyapplications.prime_access.dao.repository.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

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
import com.wdyapplications.prime_access.dao.entity.*;

/**
 * Repository customize : Mouvement.
 *
 * @author dorgeddy
 *
 */
@Repository
public interface _MouvementRepository {
	    /**
     * Finds Mouvement by using id as a search criteria.
     *
     * @param id
     * @return An Object Mouvement whose id is equals to the given id. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.id = :id and e.isDeleted = :isDeleted")
    Mouvement findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Mouvement by using typeMouvement as a search criteria.
     *
     * @param typeMouvement
     * @return An Object Mouvement whose typeMouvement is equals to the given typeMouvement. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.typeMouvement = :typeMouvement and e.isDeleted = :isDeleted")
    List<Mouvement> findByTypeMouvement(@Param("typeMouvement")String typeMouvement, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Mouvement whose createdAt is equals to the given createdAt. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
    List<Mouvement> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using createdBy as a search criteria.
     *
     * @param createdBy
     * @return An Object Mouvement whose createdBy is equals to the given createdBy. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
    List<Mouvement> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using deletedAt as a search criteria.
     *
     * @param deletedAt
     * @return An Object Mouvement whose deletedAt is equals to the given deletedAt. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
    List<Mouvement> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using isDeleted as a search criteria.
     *
     * @param isDeleted
     * @return An Object Mouvement whose isDeleted is equals to the given isDeleted. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.isDeleted = :isDeleted")
    List<Mouvement> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Mouvement whose updatedAt is equals to the given updatedAt. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
    List<Mouvement> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using updatedBy as a search criteria.
     *
     * @param updatedBy
     * @return An Object Mouvement whose updatedBy is equals to the given updatedBy. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
    List<Mouvement> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using statusId as a search criteria.
     *
     * @param statusId
     * @return An Object Mouvement whose statusId is equals to the given statusId. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.statusId = :statusId and e.isDeleted = :isDeleted")
    List<Mouvement> findByStatusId(@Param("statusId")Integer statusId, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using latitude as a search criteria.
     *
     * @param latitude
     * @return An Object Mouvement whose latitude is equals to the given latitude. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.latitude = :latitude and e.isDeleted = :isDeleted")
    List<Mouvement> findByLatitude(@Param("latitude")BigDecimal latitude, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using longitude as a search criteria.
     *
     * @param longitude
     * @return An Object Mouvement whose longitude is equals to the given longitude. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.longitude = :longitude and e.isDeleted = :isDeleted")
    List<Mouvement> findByLongitude(@Param("longitude")BigDecimal longitude, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Mouvement by using validationDate as a search criteria.
     *
     * @param validationDate
     * @return An Object Mouvement whose validationDate is equals to the given validationDate. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.validationDate = :validationDate and e.isDeleted = :isDeleted")
    List<Mouvement> findByValidationDate(@Param("validationDate")Date validationDate, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Mouvement by using personnelId as a search criteria.
     *
     * @param personnelId
     * @return An Object Mouvement whose personnelId is equals to the given personnelId. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.personnel2.id = :personnelId and e.isDeleted = :isDeleted")
    List<Mouvement> findByPersonnelId(@Param("personnelId")Integer personnelId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Mouvement by using personnelId as a search criteria.
   *
   * @param personnelId
   * @return An Object Mouvement whose personnelId is equals to the given personnelId. If
   *         no Mouvement is found, this method returns null.
   */
  @Query("select e from Mouvement e where e.personnel2.id = :personnelId and e.isDeleted = :isDeleted")
  Mouvement findMouvementByPersonnelId(@Param("personnelId")Integer personnelId, @Param("isDeleted")Boolean isDeleted);


    /**
     * Finds Mouvement by using salleId as a search criteria.
     *
     * @param salleId
     * @return An Object Mouvement whose salleId is equals to the given salleId. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.salle.id = :salleId and e.isDeleted = :isDeleted")
    List<Mouvement> findBySalleId(@Param("salleId")Integer salleId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Mouvement by using salleId as a search criteria.
   *
   * @param salleId
   * @return An Object Mouvement whose salleId is equals to the given salleId. If
   *         no Mouvement is found, this method returns null.
   */
  @Query("select e from Mouvement e where e.salle.id = :salleId and e.isDeleted = :isDeleted")
  Mouvement findMouvementBySalleId(@Param("salleId")Integer salleId, @Param("isDeleted")Boolean isDeleted);


    /**
     * Finds Mouvement by using agentSecuriteId as a search criteria.
     *
     * @param agentSecuriteId
     * @return An Object Mouvement whose agentSecuriteId is equals to the given agentSecuriteId. If
     *         no Mouvement is found, this method returns null.
     */
    @Query("select e from Mouvement e where e.personnel.id = :agentSecuriteId and e.isDeleted = :isDeleted")
    List<Mouvement> findByAgentSecuriteId(@Param("agentSecuriteId")Integer agentSecuriteId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Mouvement by using agentSecuriteId as a search criteria.
   *
   * @param agentSecuriteId
   * @return An Object Mouvement whose agentSecuriteId is equals to the given agentSecuriteId. If
   *         no Mouvement is found, this method returns null.
   */
  @Query("select e from Mouvement e where e.personnel.id = :agentSecuriteId and e.isDeleted = :isDeleted")
  Mouvement findMouvementByAgentSecuriteId(@Param("agentSecuriteId")Integer agentSecuriteId, @Param("isDeleted")Boolean isDeleted);




    default List<Map<String, Object>> nbPersonnelPresent(Request<MouvementDto> request, EntityManager em) throws ParseException {
        final MouvementDto dto = request.getData();
        List<Map<String, Object>> mapListFinal = new ArrayList<>();
        Map<String, Object> mapFinal = new HashMap<>();
        HashMap<String, Object> param = new HashMap<>();
        String querry = " SELECT\n" +
                "    COUNT(DISTINCT personnel_id) AS nb_personnes_distinctes\n" +
                "    FROM public.mouvement\n" +
                "    WHERE is_deleted IS NOT TRUE\n" +
                "    AND status_id = 1\n" +
                "    AND created_at >= date_trunc('day', now())\n" +
                "    AND created_at <  date_trunc('day', now()) + interval '1 day';";
        jakarta.persistence.TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery(querry);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<Object[]> datas = query.getResultList();
        if (!datas.isEmpty()) {
            mapFinal.put("personnel_today_count", datas.get(0));
        }
        mapListFinal.add(mapFinal);
        return mapListFinal;
    }

    default List<Map<String, Object>> nbInOutToDay(Request<MouvementDto> request, EntityManager em) throws ParseException {
        final MouvementDto dto = request.getData();
        Map<String, Object> mapFinal = new HashMap<>();
        HashMap<String, Object> param = new HashMap<>();
        List<Map<String, Object>> mapListFinal = new ArrayList<>();
        String querry = "    SELECT" +
                "    COUNT(*) FILTER (WHERE type_mouvement = 'Entrée') AS nb_Entrées," +
                "    COUNT(*) FILTER (WHERE type_mouvement = 'Sortie') AS nb_Sorties " +
                "    FROM public.mouvement " +
                "    WHERE is_deleted IS NOT TRUE" +
                "    AND created_at >= date_trunc('day', now())" +
                "    AND created_at <  date_trunc('day', now()) + interval '1 day';";
        jakarta.persistence.TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery(querry);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<Object[]> datas = query.getResultList();
        if (!datas.isEmpty()) {
            Object[] data = datas.get(0);
            mapFinal.put("nb_Entrées", data[0]);
            mapFinal.put("nb_Sorties", data[1]);
        }
        mapListFinal.add(mapFinal);
        return mapListFinal;
    }


    /**
     * Adaptation de la méthode nbSoByState pour la table "mouvement".
     *
     * HYPOTHESES posées (à ajuster selon votre DTO réel) :
     *  - MouvementDto expose : getPeriod() (hour|day|week|month|year),
     *    getCreatedAtParam().getStart()/getEnd(), et getSalleIds() (List<Integer>)
     *    pour filtrer sur des salles précises (remplace le filtre "specifications"
     *    de l'exemple d'origine). Remplacez par getPersonnelIds() si le filtre
     *    doit plutôt porter sur le personnel.
     *  - "STATE" désigne désormais type_mouvement ('Entrée' / 'Sortie').
     *  - is_deleted est un vrai bool -> comparaison "= false" (et non 'f').
     */
    default List<Map<String, Object>> nbInOutByGranularite(Request<MouvementDto> request, EntityManager em) throws ParseException {
        final MouvementDto dto = request.getData();

        // Filtre optionnel sur les salles (remplace le filtre "specifications" de l'original)
        String salleFilter = " AND C.salle_id IN (" + 1 + ") ";

        if (dto == null || dto.getCreatedAtParam() == null || dto.getCreatedAtParam().getStart() == null || dto.getCreatedAtParam().getEnd() == null) {
            throw new IllegalArgumentException("createdAtParam.start and createdAtParam.end are required");
        }

        Timestamp dateDebut = Utilities.asTimestamp(dto.getCreatedAtParam().getStart().toString());
        Timestamp dateFin = Utilities.asTimestamp(dto.getCreatedAtParam().getEnd().toString());
        String granularite = dto.getGranularite();


        Comparator<LocalDateTime> comparatorHour = new Comparator<LocalDateTime>() {
            @Override
            public int compare(LocalDateTime date1, LocalDateTime date2) {
                return date1.compareTo(date2);
            }
        };
        Comparator<Integer> comparatorWeek = new Comparator<Integer>() {
            @Override
            public int compare(Integer date1, Integer date2) {
                return date1.compareTo(date2);
            }
        };
        Comparator<String> comparatorString = new Comparator<String>() {
            @Override
            public int compare(String week1, String week2) {
                // Extraire les numéros des semaines après "S_"
                Integer weekNumber1 = Integer.parseInt(week1.substring(2));
                Integer weekNumber2 = Integer.parseInt(week2.substring(2));
                return weekNumber1.compareTo(weekNumber2);
            }
        };

        String req = "";
        HashMap<String, Object> param = new HashMap<>();
        param.put("dateDebut", dateDebut);
        param.put("dateFin", dateFin);
        if (dto.getGranularite().equals("hour")) {
            req = "SELECT " +
                    "    C.type_mouvement AS STATE," +
                    "    TO_CHAR(date_trunc('hour', C.created_at), 'MM-DD') AS theDay," +
                    "    TO_CHAR(date_trunc('hour', C.created_at), 'HH24') AS theHour," +
                    "    CONCAT('S_', TO_CHAR(C.created_at, 'WW')) AS theWeek," +
                    "    TO_CHAR(C.created_at, 'MM') AS moisString," +
                    "    TO_CHAR(C.created_at, 'YYYY') AS annee," +
                    "    TO_CHAR(C.created_at, 'YYYY-MM') AS theMois," +
                    "    DATE(C.created_at) AS jourDate," +
                    "    COUNT(C.id) AS nb " +
                    "FROM" +
                    "    mouvement C " +
                    "WHERE " +
                    "    C.is_deleted = false " +
                    "    AND C.type_mouvement IN ('Entrée','Sortie') " +
                    salleFilter +
                    "    AND C.created_at BETWEEN :dateDebut AND :dateFin" +
                    "  GROUP BY " +
                    "    C.type_mouvement," +
                    "    date_trunc('hour', C.created_at), " +
                    "    TO_CHAR(C.created_at, 'WW'), " +
                    "    TO_CHAR(C.created_at, 'MM'), " +
                    "    TO_CHAR(C.created_at, 'YYYY'), " +
                    "    TO_CHAR(C.created_at, 'YYYY-MM'), " +
                    "    DATE(C.created_at) " +
                    "ORDER BY " +
                    "    date_trunc('hour', C.created_at) ASC;";
        } else if (dto.getGranularite().equals("week")) {
            req = "SELECT  " +
                    "    C.type_mouvement AS STATE, " +
                    "    TO_CHAR(DATE_TRUNC('week', C.created_at), '\"S_\"WW') AS theWeek, " +
                    "    TO_CHAR(C.created_at, 'YYYY') AS annee, " +
                    "    COUNT(C.id) AS nb " +
                    "FROM  " +
                    "    mouvement C " +
                    "WHERE  " +
                    "    C.is_deleted = false " +
                    "    AND C.type_mouvement IN ('Entrée','Sortie') " +
                    salleFilter +
                    "    AND C.created_at BETWEEN :dateDebut AND :dateFin" +
                    "  GROUP BY " +
                    "    C.type_mouvement," +
                    "    TO_CHAR(C.created_at, 'YYYY'), " +
                    "    DATE_TRUNC('week', C.created_at) " +
                    "ORDER BY  " +
                    "    theWeek ASC;";
        } else if (dto.getGranularite().equals("year")) {
            req = "SELECT  " +
                    "    C.type_mouvement AS STATE, " +
                    "    TO_CHAR(DATE_TRUNC('year', C.created_at), 'YYYY') AS theYear, " +
                    "    COUNT(C.id) AS nb " +
                    " FROM  " +
                    "    mouvement C " +
                    "WHERE  " +
                    "    C.is_deleted = false " +
                    "    AND C.type_mouvement IN ('Entrée','Sortie') " +
                    salleFilter +
                    "    AND C.created_at BETWEEN :dateDebut AND :dateFin" +
                    "  GROUP BY  " +
                    "    C.type_mouvement," +
                    "    DATE_TRUNC('year', C.created_at) " +
                    "ORDER BY  " +
                    "    theYear ASC;";
        } else if (dto.getGranularite().equals("month")) {
            req = "SELECT  " +
                    "    C.type_mouvement AS STATE, " +
                    "    TO_CHAR(DATE_TRUNC('month', C.created_at), 'YYYY-MM') AS theMonth, " +
                    "    TO_CHAR(C.created_at, 'YYYY') AS annee, " +
                    "    COUNT(C.id) AS nb " +
                    "FROM  " +
                    "    mouvement C " +
                    "WHERE  " +
                    "    C.is_deleted = false " +
                    "    AND C.type_mouvement IN ('Entrée','Sortie') " +
                    salleFilter +
                    "    AND C.created_at BETWEEN :dateDebut AND :dateFin" +
                    "  GROUP BY  " +
                    "    C.type_mouvement," +
                    "    TO_CHAR(C.created_at, 'YYYY'), " +
                    "    DATE_TRUNC('month', C.created_at) " +
                    "ORDER BY  " +
                    "    theMonth ASC;";
        } else {
            // day (défaut)
            req = "SELECT  " +
                    "    C.type_mouvement AS STATE, " +
                    "    DATE(C.created_at) AS jourDate, " +
                    "    CONCAT('S_', TO_CHAR(C.created_at, 'WW')) AS theWeek, " +
                    "    TO_CHAR(C.created_at, 'YYYY-MM') AS theMois, " +
                    "    TO_CHAR(C.created_at, 'YYYY') AS annee, " +
                    "    COUNT(C.id) AS nb " +
                    "FROM " +
                    "    mouvement C " +
                    "WHERE " +
                    "    C.is_deleted = false " +
                    "    AND C.type_mouvement IN ('Entrée','Sortie') " +
                    salleFilter +
                    "    AND C.created_at BETWEEN :dateDebut AND :dateFin" +
                    "GROUP BY " +
                    "    C.type_mouvement, " +
                    "    DATE(C.created_at), " +
                    "    TO_CHAR(C.created_at, 'WW'), " +
                    "    TO_CHAR(C.created_at, 'YYYY-MM'), " +
                    "    TO_CHAR(C.created_at, 'YYYY') " +
                    "ORDER BY " +
                    "    annee ASC, " +
                    "    theMois ASC, " +
                    "    theWeek ASC, " +
                    "    jourDate ASC;";
        }

        System.out.println(req);
        jakarta.persistence.TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery(req);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<Object[]> datas = query.getResultList();
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Map<String, Object>> mapListFinal = new ArrayList<>();
        datas.forEach(item -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Map<String, Object> map = new HashMap<String, Object>();

            if (dto.getGranularite().equals("day")) {
                map.putIfAbsent("annee", item[4]);
                map.putIfAbsent("state", item[0]);
                map.putIfAbsent("total", item[5]);
                map.putIfAbsent("date", item[1]);
                map.putIfAbsent("normalDate", LocalDateTime.parse(item[1].toString() + " " + "00", formatter));
            }
            if (dto.getGranularite().equals("week")) {
                map.putIfAbsent("annee", item[2]);
                map.putIfAbsent("state", item[0]);
                map.putIfAbsent("total", item[3]);
                map.putIfAbsent("date", item[1].toString());
                map.putIfAbsent("normalDate", item[1].toString());
            }
            if (dto.getGranularite().equals("month")) {
                map.putIfAbsent("annee", item[2]);
                map.putIfAbsent("state", item[0]);
                map.putIfAbsent("total", item[3]);
                map.putIfAbsent("date", item[1].toString());
                map.putIfAbsent("normalDate", Integer.parseInt(item[1].toString().replaceAll("-", "")));
            }
            if (dto.getGranularite().equals("year")) {
                map.putIfAbsent("annee", item[1]);
                map.putIfAbsent("state", item[0]);
                map.putIfAbsent("total", item[2]);
                map.putIfAbsent("date", item[1].toString());
                map.putIfAbsent("normalDate", Integer.parseInt(item[1].toString().replaceAll("-", "")));
            }
            if (dto.getGranularite().equals("hour")) {
                map.putIfAbsent("annee", item[5]);
                map.putIfAbsent("state", item[0]);
                map.putIfAbsent("total", item[8]);
                map.putIfAbsent("date", item[7] + " " + item[2].toString() + "H");
                map.putIfAbsent("normalDate", LocalDateTime.parse(item[7].toString() + " " + item[2].toString(), formatter));
            }
            mapList.add(map);
        });

        Map<Object, List<Map<String, Object>>> groupedMap = mapList.stream()
                .collect(Collectors.groupingBy(map -> map.get("normalDate")));

        if (dto.getGranularite().equals("hour") || dto.getGranularite().equals("day")) {
            TreeMap<LocalDateTime, List<Map<String, Object>>> sortedMap = new TreeMap<>(comparatorHour);
            sortedMap.putAll((HashMap) groupedMap);
            sortedMap.forEach((key, value) -> {
                Map<String, Object> map = new HashMap<String, Object>();
                int total = 0;
                for (int i = 0; i < value.size(); i++) {
                    total = total + Integer.parseInt(value.get(i).get("total").toString());
                }
                map.putIfAbsent("total", total);
                map.putIfAbsent("date", value.get(0).get("date"));
                map.putIfAbsent("annee", value.get(0).get("annee"));
                map.putIfAbsent("data", value);
                mapListFinal.add(map);
            });
        } else if (dto.getGranularite().equals("month") || dto.getGranularite().equals("year")) {
            TreeMap<Integer, List<Map<String, Object>>> sortedMap = new TreeMap<>(comparatorWeek);
            sortedMap.putAll((HashMap) groupedMap);
            sortedMap.forEach((key, value) -> {
                Map<String, Object> map = new HashMap<String, Object>();
                int total = 0;
                for (int i = 0; i < value.size(); i++) {
                    total = total + Integer.parseInt(value.get(i).get("total").toString());
                }
                map.putIfAbsent("total", total);
                map.putIfAbsent("date", value.get(0).get("date"));
                map.putIfAbsent("annee", value.get(0).get("annee"));
                map.putIfAbsent("data", value);
                mapListFinal.add(map);
            });
        } else if (dto.getGranularite().equals("week")) {
            TreeMap<String, List<Map<String, Object>>> sortedMap = new TreeMap<>(comparatorString);
            sortedMap.putAll((HashMap) groupedMap);
            sortedMap.forEach((key, value) -> {
                Map<String, Object> map = new HashMap<String, Object>();
                int total = 0;
                for (int i = 0; i < value.size(); i++) {
                    total = total + Integer.parseInt(value.get(i).get("total").toString());
                }
                map.putIfAbsent("total", total);
                map.putIfAbsent("date", value.get(0).get("date"));
                map.putIfAbsent("annee", value.get(0).get("annee"));
                map.putIfAbsent("data", value);
                mapListFinal.add(map);
            });
        } else {
            TreeMap<Date, List<Map<String, Object>>> sortedMap = new TreeMap<>(Date::compareTo);
            sortedMap.putAll((HashMap) groupedMap);
            groupedMap.forEach((key, value) -> {
                Map<String, Object> map = new HashMap<String, Object>();
                int total = 0;
                for (int i = 0; i < value.size(); i++) {
                    total = total + Integer.parseInt(value.get(i).get("total").toString());
                }
                map.putIfAbsent("total", total);
                map.putIfAbsent("date", value.get(0).get("date"));
                map.putIfAbsent("annee", value.get(0).get("annee"));
                map.putIfAbsent("data", value);
                mapListFinal.add(map);
            });
        }

        return mapListFinal;
    }
    /**
     * Finds List of Mouvement by using mouvementDto as a search criteria.
     *
     * @param request, em
     * @return A List of Mouvement
     * @throws DataAccessException,ParseException
     */
    public default List<Mouvement> getByCriteria(Request<MouvementDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Mouvement e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
        TypedQuery<Mouvement> query = em.createQuery(req, Mouvement.class);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        if (request.getIndex() != null && request.getSize() != null) {
            query.setFirstResult(request.getIndex() * request.getSize());
            query.setMaxResults(request.getSize());
        }
        return query.getResultList();
    }

    /**
     * Finds count of Mouvement by using mouvementDto as a search criteria.
     *
     * @param request, em
     * @return Number of Mouvement
     *
     */
    public default Long count(Request<MouvementDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        HashMap<String, Object> param = new HashMap<String, Object>();
        String req = "select count(distinct e.id) from Mouvement e ";
        req += " where e IS NOT NULL ";

        String whereExpr = getWhereExpression(request, param, locale);
        // Remove ORDER BY clause from count queries (aggregate function cannot have ORDER BY without GROUP BY)
        whereExpr = whereExpr.replaceAll("\\s+order by\\s+.*", "");
        req += whereExpr;
        jakarta.persistence.Query query = em.createQuery(req);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        Long count = (Long) query.getResultList().get(0);
        return count;
    }

    /**
     * get where expression
     * @param request
     * @param param
     * @param locale
     * @return
     * @throws Exception
     */
    default String getWhereExpression(Request<MouvementDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        MouvementDto dto = request.getData() != null ? request.getData() : new MouvementDto();
        dto.setIsDeleted(false);
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (MouvementDto elt : request.getDatas()) {
                elt.setIsDeleted(false);
                String eltReq = generateCriteria(elt, param, index, locale);
                if (request.getIsAnd() != null && request.getIsAnd()) {
                    othersReq += "and (" + eltReq + ") ";
                } else {
                    othersReq += "or (" + eltReq + ") ";
                }
                index++;
            }
        }
        String req = "";
        if (!mainReq.isEmpty()) {
            req += " and (" + mainReq + ") ";
        }
        req += othersReq;
        req += " order by  e.id desc";
        //order
        
        return req;
    }

    /**
     * generate sql query for dto
     * @param dto
     * @param param
     * @param index
     * @param locale
     * @return
     * @throws Exception
     */
    default String generateCriteria(MouvementDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getTypeMouvement()) || Utilities.searchParamIsNotEmpty(dto.getTypeMouvementParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("typeMouvement", dto.getTypeMouvement(), "e.typeMouvement", "String", dto.getTypeMouvementParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
            }
            if (dto.getCreatedBy() != null || Utilities.searchParamIsNotEmpty(dto.getCreatedByParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getDeletedAt()) || Utilities.searchParamIsNotEmpty(dto.getDeletedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
            }
            if (dto.getIsDeleted() != null || Utilities.searchParamIsNotEmpty(dto.getIsDeletedParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
            }
            if (dto.getUpdatedBy() != null || Utilities.searchParamIsNotEmpty(dto.getUpdatedByParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
            }
            if (dto.getStatusId() != null || Utilities.searchParamIsNotEmpty(dto.getStatusIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("statusId", dto.getStatusId(), "e.statusId", "Integer", dto.getStatusIdParam(), param, index, locale));
            }
            if (dto.getLatitude() != null || Utilities.searchParamIsNotEmpty(dto.getLatitudeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("latitude", dto.getLatitude(), "e.latitude", "BigDecimal", dto.getLatitudeParam(), param, index, locale));
            }
            if (dto.getLongitude() != null || Utilities.searchParamIsNotEmpty(dto.getLongitudeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("longitude", dto.getLongitude(), "e.longitude", "BigDecimal", dto.getLongitudeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getValidationDate()) || Utilities.searchParamIsNotEmpty(dto.getValidationDateParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("validationDate", dto.getValidationDate(), "e.validationDate", "Date", dto.getValidationDateParam(), param, index, locale));
            }
                        if (dto.getPersonnelId() != null || Utilities.searchParamIsNotEmpty(dto.getPersonnelIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("personnelId", dto.getPersonnelId(), "e.personnel2.id", "Integer", dto.getPersonnelIdParam(), param, index, locale));
            }
                        if (dto.getSalleId() != null || Utilities.searchParamIsNotEmpty(dto.getSalleIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("salleId", dto.getSalleId(), "e.salle.id", "Integer", dto.getSalleIdParam(), param, index, locale));
            }
                        if (dto.getAgentSecuriteId() != null || Utilities.searchParamIsNotEmpty(dto.getAgentSecuriteIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("agentSecuriteId", dto.getAgentSecuriteId(), "e.personnel.id", "Integer", dto.getAgentSecuriteIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPersonnelNom()) || Utilities.searchParamIsNotEmpty(dto.getPersonnelNomParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("personnelNom", dto.getPersonnelNom(), "e.personnel2.nom", "String", dto.getPersonnelNomParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPersonnelPrenoms()) || Utilities.searchParamIsNotEmpty(dto.getPersonnelPrenomsParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("personnelPrenoms", dto.getPersonnelPrenoms(), "e.personnel2.prenoms", "String", dto.getPersonnelPrenomsParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getSalleLibelle()) || Utilities.searchParamIsNotEmpty(dto.getSalleLibelleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("salleLibelle", dto.getSalleLibelle(), "e.salle.libelle", "String", dto.getSalleLibelleParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPersonnelNom()) || Utilities.searchParamIsNotEmpty(dto.getPersonnelNomParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("personnelNom", dto.getPersonnelNom(), "e.personnel.nom", "String", dto.getPersonnelNomParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPersonnelPrenoms()) || Utilities.searchParamIsNotEmpty(dto.getPersonnelPrenomsParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("personnelPrenoms", dto.getPersonnelPrenoms(), "e.personnel.prenoms", "String", dto.getPersonnelPrenomsParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
