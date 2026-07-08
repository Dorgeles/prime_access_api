                                                            														
/*
 * Java business for entity table personnel 
 * Created on 2026-07-07 ( Time 23:14:25 )
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
BUSINESS for table "personnel"
 * 
 * @author dorgeddy
 *
 */

@Component
public class PersonnelBusiness implements IBasicBusiness<Request<PersonnelDto>, Response<PersonnelDto>> {

	private Response<PersonnelDto> response;
	@Autowired
	private PersonnelRepository personnelRepository;
	@Autowired
	private MouvementRepository mouvementRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
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

	public PersonnelBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Personnel by using PersonnelDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PersonnelDto> create(Request<PersonnelDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Personnel-----");

		Response<PersonnelDto> response = new Response<PersonnelDto>();
		List<Personnel>        items    = new ArrayList<Personnel>();
			
		for (PersonnelDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("nom", dto.getNom());
			fieldsToVerify.put("prenoms", dto.getPrenoms());
			fieldsToVerify.put("telephone", dto.getTelephone());
			fieldsToVerify.put("fonction", dto.getFonction());
			fieldsToVerify.put("contractant", dto.getContractant());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("statusId", dto.getStatusId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if personnel to insert do not exist
			Personnel existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("personnel id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
				Personnel entityToSave = null;
			entityToSave = PersonnelTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setStatusId(StatusEnum.ACTIVE);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Personnel> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = personnelRepository.saveAll((Iterable<Personnel>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("personnel", locale));
				response.setHasError(true);
				return response;
			}
			List<PersonnelDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PersonnelTransformer.INSTANCE.toLiteDtos(itemsSaved) : PersonnelTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Personnel-----");
		return response;
	}

	/**
	 * update Personnel by using PersonnelDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PersonnelDto> update(Request<PersonnelDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Personnel-----");

		Response<PersonnelDto> response = new Response<PersonnelDto>();
		List<Personnel>        items    = new ArrayList<Personnel>();
			
		for (PersonnelDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la personnel existe
			Personnel entityToSave = null;
			entityToSave = personnelRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("personnel id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getNom())) {
				entityToSave.setNom(dto.getNom());
			}
			if (Utilities.notBlank(dto.getPrenoms())) {
				entityToSave.setPrenoms(dto.getPrenoms());
			}
			if (Utilities.notBlank(dto.getTelephone())) {
				entityToSave.setTelephone(dto.getTelephone());
			}
			if (Utilities.notBlank(dto.getFonction())) {
				entityToSave.setFonction(dto.getFonction());
			}
			if (Utilities.notBlank(dto.getContractant())) {
				entityToSave.setContractant(dto.getContractant());
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
			List<Personnel> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = personnelRepository.saveAll((Iterable<Personnel>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("personnel", locale));
				response.setHasError(true);
				return response;
			}
			List<PersonnelDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PersonnelTransformer.INSTANCE.toLiteDtos(itemsSaved) : PersonnelTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Personnel-----");
		return response;
	}

	/**
	 * delete Personnel by using PersonnelDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PersonnelDto> delete(Request<PersonnelDto> request, Locale locale)  {
		// System.out.println("----begin delete Personnel-----");

		Response<PersonnelDto> response = new Response<PersonnelDto>();
		List<Personnel>        items    = new ArrayList<Personnel>();
			
		for (PersonnelDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la personnel existe
			Personnel existingEntity = null;

			existingEntity = personnelRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("personnel -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// mouvement
			List<Mouvement> listOfMouvement = mouvementRepository.findByAgentSecuriteId(existingEntity.getId(), false);
			if (listOfMouvement != null && !listOfMouvement.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfMouvement.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// mouvement
			List<Mouvement> listOfMouvement2 = mouvementRepository.findByAgentSecuriteId(existingEntity.getId(), false);
			if (listOfMouvement2 != null && !listOfMouvement2.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfMouvement2.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// utilisateur
			List<Utilisateur> listOfUtilisateur = utilisateurRepository.findByPersonnelId(existingEntity.getId(), false);
			if (listOfUtilisateur != null && !listOfUtilisateur.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfUtilisateur.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			personnelRepository.saveAll((Iterable<Personnel>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Personnel-----");
		return response;
	}

	/**
	 * get Personnel by using PersonnelDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PersonnelDto> getByCriteria(Request<PersonnelDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Personnel-----");

		Response<PersonnelDto> response = new Response<PersonnelDto>();
		List<Personnel> items 			 = personnelRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PersonnelDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PersonnelTransformer.INSTANCE.toLiteDtos(items) : PersonnelTransformer.INSTANCE.toDtos(items);

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
			response.setCount(personnelRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("personnel", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Personnel-----");
		return response;
	}

	/**
	 * get full PersonnelDto by using Personnel as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PersonnelDto getFullInfos(PersonnelDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
