
package com.wdyapplications.prime_access.dao.repository.base;

import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;

import com.wdyapplications.prime_access.dao.entity.Personnel;
import com.wdyapplications.prime_access.dao.entity.Setting;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wdyapplications.prime_access.utils.*;
import com.wdyapplications.prime_access.utils.dto.*;
import com.wdyapplications.prime_access.utils.contract.Request;

/**
 * Repository customize : Setting.
 *
 * @author dorgeddy
 *
 */
@Repository
public interface _SettingRepository {
	    /**
     * Finds Setting by using id as a search criteria.
     *
     * @param id
     * @return An Object Setting whose id is equals to the given id. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.id = :id and e.isDeleted = :isDeleted")
    Setting findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Setting by using code as a search criteria.
     *
     * @param code
     * @return An Object Setting whose code is equals to the given code. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.code = :code and e.isDeleted = :isDeleted")
    Setting findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using valeur as a search criteria.
     *
     * @param valeur
     * @return An Object Setting whose valeur is equals to the given valeur. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.valeur = :valeur and e.isDeleted = :isDeleted")
    List<Setting> findByValeur(@Param("valeur")String valeur, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Setting whose createdAt is equals to the given createdAt. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
    List<Setting> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using createdBy as a search criteria.
     *
     * @param createdBy
     * @return An Object Setting whose createdBy is equals to the given createdBy. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
    List<Setting> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using deletedAt as a search criteria.
     *
     * @param deletedAt
     * @return An Object Setting whose deletedAt is equals to the given deletedAt. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
    List<Setting> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using isDeleted as a search criteria.
     *
     * @param isDeleted
     * @return An Object Setting whose isDeleted is equals to the given isDeleted. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.isDeleted = :isDeleted")
    List<Setting> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Setting whose updatedAt is equals to the given updatedAt. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
    List<Setting> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using updatedBy as a search criteria.
     *
     * @param updatedBy
     * @return An Object Setting whose updatedBy is equals to the given updatedBy. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
    List<Setting> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Setting by using statusId as a search criteria.
     *
     * @param statusId
     * @return An Object Setting whose statusId is equals to the given statusId. If
     *         no Setting is found, this method returns null.
     */
    @Query("select e from Setting e where e.statusId = :statusId and e.isDeleted = :isDeleted")
    List<Setting> findByStatusId(@Param("statusId")Integer statusId, @Param("isDeleted")Boolean isDeleted);



    /**
     * Finds List of Setting by using settingDto as a search criteria.
     *
     * @param request, em
     * @return A List of Setting
     * @throws DataAccessException,ParseException
     */
    public default List<Setting> getByCriteria(Request<SettingDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Setting e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
        TypedQuery<Setting> query = em.createQuery(req, Setting.class);
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
     * Finds count of Setting by using settingDto as a search criteria.
     *
     * @param request, em
     * @return Number of Setting
     *
     */
    public default Long count(Request<SettingDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String whereClause = "select e.id from Setting e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        // ✅ Wrapper pour ignorer le group by / order by générés
        String req = "select count(e.id) from Setting e where e.id IN (" + whereClause + ")";
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
    default String getWhereExpression(Request<SettingDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        SettingDto dto = request.getData() != null ? request.getData() : new SettingDto();
        dto.setIsDeleted(false);
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (SettingDto elt : request.getDatas()) {
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
    default String generateCriteria(SettingDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getCode()) || Utilities.searchParamIsNotEmpty(dto.getCodeParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getValeur()) || Utilities.searchParamIsNotEmpty(dto.getValeurParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("valeur", dto.getValeur(), "e.valeur", "String", dto.getValeurParam(), param, index, locale));
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

            /*List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
            if (Utilities.isNotEmpty(listOfCustomQuery)) {
                listOfQuery.addAll(listOfCustomQuery);
            }*/
        }
        return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
    }
}
