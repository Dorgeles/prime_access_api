                                                        													
/*
 * Java business for entity table utilisateur 
 * Created on 2026-07-07 ( Time 23:14:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.business;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.wdyapplications.prime_access.utils.*;
import com.wdyapplications.prime_access.utils.dto.*;
import com.wdyapplications.prime_access.utils.enums.*;
import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.contract.IBasicBusiness;
import com.wdyapplications.prime_access.utils.contract.Request;
import com.wdyapplications.prime_access.utils.contract.Response;
import com.wdyapplications.prime_access.utils.dto.transformer.*;
import com.wdyapplications.prime_access.dao.entity.*;
import com.wdyapplications.prime_access.dao.repository.*;

/**
BUSINESS for table "utilisateur"
 * 
 * @author dorgeddy
 *
 */

@Component
public class UtilisateurBusiness implements IBasicBusiness<Request<UtilisateurDto>, Response<UtilisateurDto>> {

	private Response<UtilisateurDto> response;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private PersonnelRepository personnelRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public UtilisateurBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Utilisateur by using UtilisateurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UtilisateurDto> create(Request<UtilisateurDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Utilisateur-----");

		Response<UtilisateurDto> response = new Response<UtilisateurDto>();
		List<Utilisateur>        items    = new ArrayList<Utilisateur>();
			
		for (UtilisateurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("password", dto.getPassword());
			fieldsToVerify.put("role", dto.getRole());
			fieldsToVerify.put("personnelId", dto.getPersonnelId());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("statusId", dto.getStatusId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if utilisateur to insert do not exist
			Utilisateur existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("utilisateur id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique login in db
			existingEntity = utilisateurRepository.findByLogin(dto.getLogin(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("utilisateur login -> " + dto.getLogin(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique login in items to save
			if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if personnel exist
			Personnel existingPersonnel = null;
			if (dto.getPersonnelId() != null && dto.getPersonnelId() > 0){
				existingPersonnel = personnelRepository.findOne(dto.getPersonnelId(), false);
				if (existingPersonnel == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("personnel personnelId -> " + dto.getPersonnelId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Utilisateur entityToSave = null;
			entityToSave = UtilisateurTransformer.INSTANCE.toEntity(dto, existingPersonnel);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setStatusId(StatusEnum.ACTIVE);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Utilisateur> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = utilisateurRepository.saveAll((Iterable<Utilisateur>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("utilisateur", locale));
				response.setHasError(true);
				return response;
			}
			List<UtilisateurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UtilisateurTransformer.INSTANCE.toLiteDtos(itemsSaved) : UtilisateurTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				String errorMessage = listOfError.stream()
						.distinct()
						.map(Object::toString)
						.collect(Collectors.joining(", "));
				throw new RuntimeException(errorMessage);
			}
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		// System.out.println("----end create Utilisateur-----");
		return response;
	}

	/**
	 * update Utilisateur by using UtilisateurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UtilisateurDto> update(Request<UtilisateurDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Utilisateur-----");

		Response<UtilisateurDto> response = new Response<UtilisateurDto>();
		List<Utilisateur>        items    = new ArrayList<Utilisateur>();
			
		for (UtilisateurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la utilisateur existe
			Utilisateur entityToSave = null;
			entityToSave = utilisateurRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("utilisateur id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if personnel exist
			if (dto.getPersonnelId() != null && dto.getPersonnelId() > 0){
				Personnel existingPersonnel = personnelRepository.findOne(dto.getPersonnelId(), false);
				if (existingPersonnel == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("personnel personnelId -> " + dto.getPersonnelId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setPersonnel(existingPersonnel);
			}
			if (Utilities.notBlank(dto.getLogin())) {
				entityToSave.setLogin(dto.getLogin());
			}
			if (Utilities.notBlank(dto.getPassword())) {
				entityToSave.setPassword(dto.getPassword());
			}
			if (Utilities.notBlank(dto.getRole())) {
				entityToSave.setRole(dto.getRole());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (dto.getStatusId() != null && dto.getStatusId() > 0) {
				entityToSave.setStatusId(dto.getStatusId());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Utilisateur> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = utilisateurRepository.saveAll((Iterable<Utilisateur>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("utilisateur", locale));
				response.setHasError(true);
				return response;
			}
			List<UtilisateurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UtilisateurTransformer.INSTANCE.toLiteDtos(itemsSaved) : UtilisateurTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				String errorMessage = listOfError.stream()
						.distinct()
						.map(Object::toString)
						.collect(Collectors.joining(", "));
				throw new RuntimeException(errorMessage);
			}
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		// System.out.println("----end update Utilisateur-----");
		return response;
	}

	/**
	 * delete Utilisateur by using UtilisateurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UtilisateurDto> delete(Request<UtilisateurDto> request, Locale locale)  {
		// System.out.println("----begin delete Utilisateur-----");

		Response<UtilisateurDto> response = new Response<UtilisateurDto>();
		List<Utilisateur>        items    = new ArrayList<Utilisateur>();
			
		for (UtilisateurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la utilisateur existe
			Utilisateur existingEntity = null;

			existingEntity = utilisateurRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("utilisateur -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			utilisateurRepository.saveAll((Iterable<Utilisateur>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Utilisateur-----");
		return response;
	}

	/**
	 * get Utilisateur by using UtilisateurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UtilisateurDto> getByCriteria(Request<UtilisateurDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Utilisateur-----");

		Response<UtilisateurDto> response = new Response<UtilisateurDto>();
		List<Utilisateur> items 			 = utilisateurRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<UtilisateurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UtilisateurTransformer.INSTANCE.toLiteDtos(items) : UtilisateurTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				String errorMessage = listOfError.stream()
						.distinct()
						.map(Object::toString)
						.collect(Collectors.joining(", "));
				throw new RuntimeException(errorMessage);
			}
			response.setItems(itemsDto);
			response.setCount(utilisateurRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("utilisateur", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Utilisateur-----");
		return response;
	}

	/**
	 * get full UtilisateurDto by using Utilisateur as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UtilisateurDto getFullInfos(UtilisateurDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
