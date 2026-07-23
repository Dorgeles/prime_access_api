
package com.wdyapplications.prime_access.dao.repository.base;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
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
 * Repository customize : Salle.
 *
 * @author dorgeddy
 *
 */
@Repository
public interface _SalleRepository {
	    /**
     * Finds Salle by using id as a search criteria.
     *
     * @param id
     * @return An Object Salle whose id is equals to the given id. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.id = :id and e.isDeleted = :isDeleted")
    Salle findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Salle by using libelle as a search criteria.
     *
     * @param libelle
     * @return An Object Salle whose libelle is equals to the given libelle. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.libelle = :libelle and e.isDeleted = :isDeleted")
    Salle findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using service as a search criteria.
     *
     * @param service
     * @return An Object Salle whose service is equals to the given service. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.service = :service and e.isDeleted = :isDeleted")
    List<Salle> findByService(@Param("service")String service, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using capacite as a search criteria.
     *
     * @param capacite
     * @return An Object Salle whose capacite is equals to the given capacite. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.capacite = :capacite and e.isDeleted = :isDeleted")
    List<Salle> findByCapacite(@Param("capacite")Short capacite, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Salle whose createdAt is equals to the given createdAt. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
    List<Salle> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using createdBy as a search criteria.
     *
     * @param createdBy
     * @return An Object Salle whose createdBy is equals to the given createdBy. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
    List<Salle> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using deletedAt as a search criteria.
     *
     * @param deletedAt
     * @return An Object Salle whose deletedAt is equals to the given deletedAt. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
    List<Salle> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using isDeleted as a search criteria.
     *
     * @param isDeleted
     * @return An Object Salle whose isDeleted is equals to the given isDeleted. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.isDeleted = :isDeleted")
    List<Salle> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Salle whose updatedAt is equals to the given updatedAt. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
    List<Salle> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using updatedBy as a search criteria.
     *
     * @param updatedBy
     * @return An Object Salle whose updatedBy is equals to the given updatedBy. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
    List<Salle> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Salle by using statusId as a search criteria.
     *
     * @param statusId
     * @return An Object Salle whose statusId is equals to the given statusId. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.statusId = :statusId and e.isDeleted = :isDeleted")
    List<Salle> findByStatusId(@Param("statusId")Integer statusId, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Salle by using siteId as a search criteria.
     *
     * @param siteId
     * @return An Object Salle whose siteId is equals to the given siteId. If
     *         no Salle is found, this method returns null.
     */
    @Query("select e from Salle e where e.site.id = :siteId and e.isDeleted = :isDeleted")
    List<Salle> findBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Salle by using siteId as a search criteria.
   *
   * @param siteId
   * @return An Object Salle whose siteId is equals to the given siteId. If
   *         no Salle is found, this method returns null.
   */
  @Query("select e from Salle e where e.site.id = :siteId and e.isDeleted = :isDeleted")
  Salle findSalleBySiteId(@Param("siteId")Integer siteId, @Param("isDeleted")Boolean isDeleted);




    /**
     * Finds List of Salle by using salleDto as a search criteria.
     *
     * @param request, em
     * @return A List of Salle
     * @throws DataAccessException,ParseException
     */
    public default List<Salle> getByCriteria(Request<SalleDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Salle e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
        TypedQuery<Salle> query = em.createQuery(req, Salle.class);
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
     * Finds count of Salle by using salleDto as a search criteria.
     *
     * @param request, em
     * @return Number of Salle
     *
     */
    public default Long count(Request<SalleDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        HashMap<String, Object> param = new HashMap<String, Object>();
        String req = "select count(distinct e.id) from Salle e ";
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
    default String getWhereExpression(Request<SalleDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        SalleDto dto = request.getData() != null ? request.getData() : new SalleDto();
        dto.setIsDeleted(false);
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (SalleDto elt : request.getDatas()) {
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
    default String generateCriteria(SalleDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLibelle()) || Utilities.searchParamIsNotEmpty(dto.getLibelleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getService()) || Utilities.searchParamIsNotEmpty(dto.getServiceParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("service", dto.getService(), "e.service", "String", dto.getServiceParam(), param, index, locale));
            }
            if (dto.getCapacite() != null || Utilities.searchParamIsNotEmpty(dto.getCapaciteParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("capacite", dto.getCapacite(), "e.capacite", "Short", dto.getCapaciteParam(), param, index, locale));
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
                        if (dto.getSiteId() != null || Utilities.searchParamIsNotEmpty(dto.getSiteIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("siteId", dto.getSiteId(), "e.site.id", "Integer", dto.getSiteIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getSiteLibelle()) || Utilities.searchParamIsNotEmpty(dto.getSiteLibelleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("siteLibelle", dto.getSiteLibelle(), "e.site.libelle", "String", dto.getSiteLibelleParam(), param, index, locale));
            }

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
