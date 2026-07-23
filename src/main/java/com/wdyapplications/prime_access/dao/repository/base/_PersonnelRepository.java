
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
 * Repository customize : Personnel.
 *
 * @author dorgeddy
 *
 */
@Repository
public interface _PersonnelRepository {
	    /**
     * Finds Personnel by using id as a search criteria.
     *
     * @param id
     * @return An Object Personnel whose id is equals to the given id. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.id = :id and e.isDeleted = :isDeleted")
    Personnel findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Personnel by using nom as a search criteria.
     *
     * @param nom
     * @return An Object Personnel whose nom is equals to the given nom. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.nom = :nom and e.isDeleted = :isDeleted")
    List<Personnel> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using imageUrl as a search criteria.
     *
     * @param imageUrl
     * @return An Object Personnel whose imageUrl is equals to the given nom. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.imageUrl = :imageUrl and e.isDeleted = :isDeleted")
    List<Personnel> findByImageUrl(@Param("imageUrl")String imageUrl, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using prenoms as a search criteria.
     *
     * @param prenoms
     * @return An Object Personnel whose prenoms is equals to the given prenoms. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.prenoms = :prenoms and e.isDeleted = :isDeleted")
    List<Personnel> findByPrenoms(@Param("prenoms")String prenoms, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using telephone as a search criteria.
     *
     * @param telephone
     * @return An Object Personnel whose telephone is equals to the given telephone. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.telephone = :telephone and e.isDeleted = :isDeleted")
    Personnel findByTelephone(@Param("telephone")String telephone, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using email as a search criteria.
     *
     * @param email
     * @return An Object Personnel whose telephone is equals to the given telephone. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.email = :email and e.isDeleted = :isDeleted")
    Personnel findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using fonction as a search criteria.
     *
     * @param fonction
     * @return An Object Personnel whose fonction is equals to the given fonction. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.fonction = :fonction and e.isDeleted = :isDeleted")
    List<Personnel> findByFonction(@Param("fonction")String fonction, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using contractant as a search criteria.
     *
     * @param contractant
     * @return An Object Personnel whose contractant is equals to the given contractant. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.contractant = :contractant and e.isDeleted = :isDeleted")
    List<Personnel> findByContractant(@Param("contractant")String contractant, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Personnel whose createdAt is equals to the given createdAt. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
    List<Personnel> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using createdBy as a search criteria.
     *
     * @param createdBy
     * @return An Object Personnel whose createdBy is equals to the given createdBy. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
    List<Personnel> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using deletedAt as a search criteria.
     *
     * @param deletedAt
     * @return An Object Personnel whose deletedAt is equals to the given deletedAt. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
    List<Personnel> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using isDeleted as a search criteria.
     *
     * @param isDeleted
     * @return An Object Personnel whose isDeleted is equals to the given isDeleted. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.isDeleted = :isDeleted")
    List<Personnel> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Personnel whose updatedAt is equals to the given updatedAt. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
    List<Personnel> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using updatedBy as a search criteria.
     *
     * @param updatedBy
     * @return An Object Personnel whose updatedBy is equals to the given updatedBy. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
    List<Personnel> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Personnel by using statusId as a search criteria.
     *
     * @param statusId
     * @return An Object Personnel whose statusId is equals to the given statusId. If
     *         no Personnel is found, this method returns null.
     */
    @Query("select e from Personnel e where e.statusId = :statusId and e.isDeleted = :isDeleted")
    List<Personnel> findByStatusId(@Param("statusId")Integer statusId, @Param("isDeleted")Boolean isDeleted);


    /**
     * Finds List of Personnel by using personnelDto as a search criteria.
     *
     * @param request, em
     * @return A List of Personnel
     * @throws DataAccessException,ParseException
     */
    public default List<Personnel> getByCriteria(Request<PersonnelDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Personnel e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
        TypedQuery<Personnel> query = em.createQuery(req, Personnel.class);
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
     * Finds count of Personnel by using personnelDto as a search criteria.
     *
     * @param request, em
     * @return Number of Personnel
     *
     */
    public default Long count(Request<PersonnelDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        HashMap<String, Object> param = new HashMap<String, Object>();
        String req = "select count(distinct e.id) from Personnel e ";
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
    default String getWhereExpression(Request<PersonnelDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        PersonnelDto dto = request.getData() != null ? request.getData() : new PersonnelDto();
        dto.setIsDeleted(false);
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (PersonnelDto elt : request.getDatas()) {
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
    default String generateCriteria(PersonnelDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getNom()) || Utilities.searchParamIsNotEmpty(dto.getNomParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPrenoms()) || Utilities.searchParamIsNotEmpty(dto.getPrenomsParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("prenoms", dto.getPrenoms(), "e.prenoms", "String", dto.getPrenomsParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getTelephone()) || Utilities.searchParamIsNotEmpty(dto.getTelephoneParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("telephone", dto.getTelephone(), "e.telephone", "String", dto.getTelephoneParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getEmail()) || Utilities.searchParamIsNotEmpty(dto.getEmailParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getFonction()) || Utilities.searchParamIsNotEmpty(dto.getFonctionParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("fonction", dto.getFonction(), "e.fonction", "String", dto.getFonctionParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getContractant()) || Utilities.searchParamIsNotEmpty(dto.getContractantParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("contractant", dto.getContractant(), "e.contractant", "String", dto.getContractantParam(), param, index, locale));
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
