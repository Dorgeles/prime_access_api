                                                        													
/*
 * Java business for entity table salle 
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
BUSINESS for table "salle"
 * 
 * @author dorgeddy
 *
 */

@Component
public class SalleBusiness implements IBasicBusiness<Request<SalleDto>, Response<SalleDto>> {

	private Response<SalleDto> response;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private MouvementRepository mouvementRepository;
	@Autowired
	private SiteRepository siteRepository;
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

	public SalleBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Salle by using SalleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SalleDto> create(Request<SalleDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Salle-----");

		Response<SalleDto> response = new Response<SalleDto>();
		List<Salle>        items    = new ArrayList<Salle>();
			
		for (SalleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("service", dto.getService());
			fieldsToVerify.put("capacite", dto.getCapacite());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("statusId", dto.getStatusId());
			fieldsToVerify.put("siteId", dto.getSiteId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if salle to insert do not exist
			Salle existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("salle id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique libelle in db
			existingEntity = salleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("salle libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if site exist
			Site existingSite = null;
			if (dto.getSiteId() != null && dto.getSiteId() > 0){
				existingSite = siteRepository.findOne(dto.getSiteId(), false);
				if (existingSite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Salle entityToSave = null;
			entityToSave = SalleTransformer.INSTANCE.toEntity(dto, existingSite);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setStatusId(StatusEnum.ACTIVE);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Salle> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = salleRepository.saveAll((Iterable<Salle>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("salle", locale));
				response.setHasError(true);
				return response;
			}
			List<SalleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SalleTransformer.INSTANCE.toLiteDtos(itemsSaved) : SalleTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Salle-----");
		return response;
	}

	/**
	 * update Salle by using SalleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SalleDto> update(Request<SalleDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Salle-----");

		Response<SalleDto> response = new Response<SalleDto>();
		List<Salle>        items    = new ArrayList<Salle>();
			
		for (SalleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la salle existe
			Salle entityToSave = null;
			entityToSave = salleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("salle id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if site exist
			if (dto.getSiteId() != null && dto.getSiteId() > 0){
				Site existingSite = siteRepository.findOne(dto.getSiteId(), false);
				if (existingSite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("site siteId -> " + dto.getSiteId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setSite(existingSite);
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getService())) {
				entityToSave.setService(dto.getService());
			}
			if (dto.getCapacite() != null && dto.getCapacite() > 0) {
				entityToSave.setCapacite(dto.getCapacite());
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
			List<Salle> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = salleRepository.saveAll((Iterable<Salle>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("salle", locale));
				response.setHasError(true);
				return response;
			}
			List<SalleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SalleTransformer.INSTANCE.toLiteDtos(itemsSaved) : SalleTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Salle-----");
		return response;
	}

	/**
	 * delete Salle by using SalleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SalleDto> delete(Request<SalleDto> request, Locale locale)  {
		// System.out.println("----begin delete Salle-----");

		Response<SalleDto> response = new Response<SalleDto>();
		List<Salle>        items    = new ArrayList<Salle>();
			
		for (SalleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la salle existe
			Salle existingEntity = null;

			existingEntity = salleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("salle -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// mouvement
			List<Mouvement> listOfMouvement = mouvementRepository.findBySalleId(existingEntity.getId(), false);
			if (listOfMouvement != null && !listOfMouvement.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfMouvement.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			salleRepository.saveAll((Iterable<Salle>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Salle-----");
		return response;
	}

	/**
	 * get Salle by using SalleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SalleDto> getByCriteria(Request<SalleDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Salle-----");

		Response<SalleDto> response = new Response<SalleDto>();
		List<Salle> items 			 = salleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<SalleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SalleTransformer.INSTANCE.toLiteDtos(items) : SalleTransformer.INSTANCE.toDtos(items);

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
			response.setCount(salleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("salle", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Salle-----");
		return response;
	}

	/**
	 * get full SalleDto by using Salle as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private SalleDto getFullInfos(SalleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
