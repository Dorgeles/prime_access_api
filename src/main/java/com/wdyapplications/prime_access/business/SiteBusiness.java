                                                        													
/*
 * Java business for entity table site 
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
BUSINESS for table "site"
 * 
 * @author dorgeddy
 *
 */

@Component
public class SiteBusiness implements IBasicBusiness<Request<SiteDto>, Response<SiteDto>> {

	private Response<SiteDto> response;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private SalleRepository salleRepository;
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

	public SiteBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Site by using SiteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SiteDto> create(Request<SiteDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Site-----");

		Response<SiteDto> response = new Response<SiteDto>();
		List<Site>        items    = new ArrayList<Site>();
			
		for (SiteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("adresse", dto.getAdresse());
			fieldsToVerify.put("latitude", dto.getLatitude());
			fieldsToVerify.put("longitude", dto.getLongitude());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if site to insert do not exist
			Site existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("site id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique libelle in db
			existingEntity = siteRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("site libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

				Site entityToSave = null;
			entityToSave = SiteTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setStatusId(StatusEnum.ACTIVE);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Site> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = siteRepository.saveAll((Iterable<Site>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("site", locale));
				response.setHasError(true);
				return response;
			}
			List<SiteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SiteTransformer.INSTANCE.toLiteDtos(itemsSaved) : SiteTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end create Site-----");
		return response;
	}

	/**
	 * update Site by using SiteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SiteDto> update(Request<SiteDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Site-----");

		Response<SiteDto> response = new Response<SiteDto>();
		List<Site>        items    = new ArrayList<Site>();
			
		for (SiteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la site existe
			Site entityToSave = null;
			entityToSave = siteRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("site id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getAdresse())) {
				entityToSave.setAdresse(dto.getAdresse());
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
			if (Utilities.isValid(dto.getLatitude())) {
				entityToSave.setLatitude(dto.getLatitude());
			}
			if (Utilities.isValid(dto.getLongitude())) {
				entityToSave.setLongitude(dto.getLongitude());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Site> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = siteRepository.saveAll((Iterable<Site>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("site", locale));
				response.setHasError(true);
				return response;
			}
			List<SiteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SiteTransformer.INSTANCE.toLiteDtos(itemsSaved) : SiteTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Site-----");
		return response;
	}

	/**
	 * delete Site by using SiteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SiteDto> delete(Request<SiteDto> request, Locale locale)  {
		// System.out.println("----begin delete Site-----");

		Response<SiteDto> response = new Response<SiteDto>();
		List<Site>        items    = new ArrayList<Site>();
			
		for (SiteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la site existe
			Site existingEntity = null;

			existingEntity = siteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("site -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// salle
			List<Salle> listOfSalle = salleRepository.findBySiteId(existingEntity.getId(), false);
			if (listOfSalle != null && !listOfSalle.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfSalle.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			siteRepository.saveAll((Iterable<Site>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Site-----");
		return response;
	}

	/**
	 * get Site by using SiteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SiteDto> getByCriteria(Request<SiteDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Site-----");

		Response<SiteDto> response = new Response<SiteDto>();
		List<Site> items 			 = siteRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<SiteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SiteTransformer.INSTANCE.toLiteDtos(items) : SiteTransformer.INSTANCE.toDtos(items);

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
			response.setCount(siteRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("site", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Site-----");
		return response;
	}

	/**
	 * get full SiteDto by using Site as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private SiteDto getFullInfos(SiteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
