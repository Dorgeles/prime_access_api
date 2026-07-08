
package com.wdyapplications.prime_access.dao.repository.base;

import java.math.BigDecimal;
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
 * Repository customize : Site.
 *
 * @author dorgeddy
 *
 */
@Repository
public interface _SiteRepository {
	    /**
     * Finds Site by using id as a search criteria.
     *
     * @param id
     * @return An Object Site whose id is equals to the given id. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.id = :id and e.isDeleted = :isDeleted")
    Site findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Site by using libelle as a search criteria.
     *
     * @param libelle
     * @return An Object Site whose libelle is equals to the given libelle. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.libelle = :libelle and e.isDeleted = :isDeleted")
    Site findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using adresse as a search criteria.
     *
     * @param adresse
     * @return An Object Site whose adresse is equals to the given adresse. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.adresse = :adresse and e.isDeleted = :isDeleted")
    List<Site> findByAdresse(@Param("adresse")String adresse, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Site whose createdAt is equals to the given createdAt. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
    List<Site> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using createdBy as a search criteria.
     *
     * @param createdBy
     * @return An Object Site whose createdBy is equals to the given createdBy. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
    List<Site> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using deletedAt as a search criteria.
     *
     * @param deletedAt
     * @return An Object Site whose deletedAt is equals to the given deletedAt. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
    List<Site> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using isDeleted as a search criteria.
     *
     * @param isDeleted
     * @return An Object Site whose isDeleted is equals to the given isDeleted. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.isDeleted = :isDeleted")
    List<Site> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Site whose updatedAt is equals to the given updatedAt. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
    List<Site> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using updatedBy as a search criteria.
     *
     * @param updatedBy
     * @return An Object Site whose updatedBy is equals to the given updatedBy. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
    List<Site> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using statusId as a search criteria.
     *
     * @param statusId
     * @return An Object Site whose statusId is equals to the given statusId. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.statusId = :statusId and e.isDeleted = :isDeleted")
    List<Site> findByStatusId(@Param("statusId")Integer statusId, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using latitude as a search criteria.
     *
     * @param latitude
     * @return An Object Site whose latitude is equals to the given latitude. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.latitude = :latitude and e.isDeleted = :isDeleted")
    List<Site> findByLatitude(@Param("latitude")BigDecimal latitude, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Site by using longitude as a search criteria.
     *
     * @param longitude
     * @return An Object Site whose longitude is equals to the given longitude. If
     *         no Site is found, this method returns null.
     */
    @Query("select e from Site e where e.longitude = :longitude and e.isDeleted = :isDeleted")
    List<Site> findByLongitude(@Param("longitude")BigDecimal longitude, @Param("isDeleted")Boolean isDeleted);



    /**
     * Finds List of Site by using siteDto as a search criteria.
     *
     * @param request, em
     * @return A List of Site
     * @throws DataAccessException,ParseException
     */
    public default List<Site> getByCriteria(Request<SiteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Site e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
                TypedQuery<Site> query = em.createQuery(req, Site.class);
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
     * Finds count of Site by using siteDto as a search criteria.
     *
     * @param request, em
     * @return Number of Site
     *
     */
    public default Long count(Request<SiteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String req = "select count(e.id) from Site e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
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
    default String getWhereExpression(Request<SiteDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        SiteDto dto = request.getData() != null ? request.getData() : new SiteDto();
        dto.setIsDeleted(false);
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (SiteDto elt : request.getDatas()) {
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
        if(Direction.fromOptionalString(dto.getOrderDirection()).orElse(null) != null && Utilities.notBlank(dto.getOrderField())) {
            req += " group by  e.id";
            req += " order by e."+dto.getOrderField()+" "+dto.getOrderDirection();
        }
        else {
            req += " group by  e.id desc";
            req += " order by  e.id desc";
        }
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
    default String generateCriteria(SiteDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLibelle()) || Utilities.searchParamIsNotEmpty(dto.getLibelleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getAdresse()) || Utilities.searchParamIsNotEmpty(dto.getAdresseParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("adresse", dto.getAdresse(), "e.adresse", "String", dto.getAdresseParam(), param, index, locale));
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

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
