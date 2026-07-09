
package com.wdyapplications.prime_access.dao.repository.base;

import java.util.Date;
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
 * Repository customize : Utilisateur.
 *
 * @author dorgeddy
 *
 */
@Repository
public interface _UtilisateurRepository {
	    /**
     * Finds Utilisateur by using id as a search criteria.
     *
     * @param id
     * @return An Object Utilisateur whose id is equals to the given id. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.id = :id and e.isDeleted = :isDeleted")
    Utilisateur findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Utilisateur by using login as a search criteria.
     *
     * @param login
     * @return An Object Utilisateur whose login is equals to the given login. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.login = :login and e.isDeleted = :isDeleted")
    Utilisateur findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using password as a search criteria.
     *
     * @param password
     * @return An Object Utilisateur whose password is equals to the given password. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.password = :password and e.isDeleted = :isDeleted")
    List<Utilisateur> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using role as a search criteria.
     *
     * @param role
     * @return An Object Utilisateur whose role is equals to the given role. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.role = :role and e.isDeleted = :isDeleted")
    List<Utilisateur> findByRole(@Param("role")String role, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using createdAt as a search criteria.
     *
     * @param createdAt
     * @return An Object Utilisateur whose createdAt is equals to the given createdAt. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
    List<Utilisateur> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using createdBy as a search criteria.
     *
     * @param createdBy
     * @return An Object Utilisateur whose createdBy is equals to the given createdBy. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
    List<Utilisateur> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using deletedAt as a search criteria.
     *
     * @param deletedAt
     * @return An Object Utilisateur whose deletedAt is equals to the given deletedAt. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
    List<Utilisateur> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using isDeleted as a search criteria.
     *
     * @param isDeleted
     * @return An Object Utilisateur whose isDeleted is equals to the given isDeleted. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.isDeleted = :isDeleted")
    List<Utilisateur> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using updatedAt as a search criteria.
     *
     * @param updatedAt
     * @return An Object Utilisateur whose updatedAt is equals to the given updatedAt. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
    List<Utilisateur> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using updatedBy as a search criteria.
     *
     * @param updatedBy
     * @return An Object Utilisateur whose updatedBy is equals to the given updatedBy. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
    List<Utilisateur> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
    /**
     * Finds Utilisateur by using statusId as a search criteria.
     *
     * @param statusId
     * @return An Object Utilisateur whose statusId is equals to the given statusId. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.statusId = :statusId and e.isDeleted = :isDeleted")
    List<Utilisateur> findByStatusId(@Param("statusId")Integer statusId, @Param("isDeleted")Boolean isDeleted);

    /**
     * Finds Utilisateur by using personnelId as a search criteria.
     *
     * @param personnelId
     * @return An Object Utilisateur whose personnelId is equals to the given personnelId. If
     *         no Utilisateur is found, this method returns null.
     */
    @Query("select e from Utilisateur e where e.personnel.id = :personnelId and e.isDeleted = :isDeleted")
    List<Utilisateur> findByPersonnelId(@Param("personnelId")Integer personnelId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Utilisateur by using personnelId as a search criteria.
   *
   * @param personnelId
   * @return An Object Utilisateur whose personnelId is equals to the given personnelId. If
   *         no Utilisateur is found, this method returns null.
   */
  @Query("select e from Utilisateur e where e.personnel.id = :personnelId and e.isDeleted = :isDeleted")
  Utilisateur findUtilisateurByPersonnelId(@Param("personnelId")Integer personnelId, @Param("isDeleted")Boolean isDeleted);




    /**
     * Finds List of Utilisateur by using utilisateurDto as a search criteria.
     *
     * @param request, em
     * @return A List of Utilisateur
     * @throws DataAccessException,ParseException
     */
    public default List<Utilisateur> getByCriteria(Request<UtilisateurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
        String req = "select e from Utilisateur e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        req += getWhereExpression(request, param, locale);
        TypedQuery<Utilisateur> query = em.createQuery(req, Utilisateur.class);
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
     * Finds count of Utilisateur by using utilisateurDto as a search criteria.
     *
     * @param request, em
     * @return Number of Utilisateur
     *
     */
    public default Long count(Request<UtilisateurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
        String whereClause = "select e.id from Utilisateur e where e IS NOT NULL";
        HashMap<String, Object> param = new HashMap<String, Object>();
        // ✅ Wrapper pour ignorer le group by / order by générés
        String req = "select count(e.id) from Utilisateur e where e.id IN (" + whereClause + ")";
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
    default String getWhereExpression(Request<UtilisateurDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
        // main query
        UtilisateurDto dto = request.getData() != null ? request.getData() : new UtilisateurDto();
        dto.setIsDeleted(false);
        String mainReq = generateCriteria(dto, param, 0, locale);
        // others query
        String othersReq = "";
        if (request.getDatas() != null && !request.getDatas().isEmpty()) {
            Integer index = 1;
            for (UtilisateurDto elt : request.getDatas()) {
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
    default String generateCriteria(UtilisateurDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
        List<String> listOfQuery = new ArrayList<String>();
        if (dto != null) {
            if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getLogin()) || Utilities.searchParamIsNotEmpty(dto.getLoginParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("login", dto.getLogin(), "e.login", "String", dto.getLoginParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getPassword()) || Utilities.searchParamIsNotEmpty(dto.getPasswordParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("password", dto.getPassword(), "e.password", "String", dto.getPasswordParam(), param, index, locale));
            }
            if (Utilities.isNotBlank(dto.getRole()) || Utilities.searchParamIsNotEmpty(dto.getRoleParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("role", dto.getRole(), "e.role", "String", dto.getRoleParam(), param, index, locale));
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
                        if (dto.getPersonnelId() != null || Utilities.searchParamIsNotEmpty(dto.getPersonnelIdParam())) {
                listOfQuery.add(CriteriaUtils.generateCriteria("personnelId", dto.getPersonnelId(), "e.personnel.id", "Integer", dto.getPersonnelIdParam(), param, index, locale));
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
