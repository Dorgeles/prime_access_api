                                                											
/*
 * Java business for entity table setting 
 * Created on 2026-07-08 ( Time 08:36:55 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.business;

import com.wdyapplications.prime_access.dao.entity.Personnel;

import com.wdyapplications.prime_access.dao.entity.Setting;
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
import com.wdyapplications.prime_access.utils.contract.IBasicBusiness;
import com.wdyapplications.prime_access.utils.contract.Request;
import com.wdyapplications.prime_access.utils.contract.Response;
import com.wdyapplications.prime_access.utils.dto.transformer.*;
import com.wdyapplications.prime_access.dao.repository.*;

/**
BUSINESS for table "setting"
 * 
 * @author dorgeddy
 *
 */

@Component
public class SettingBusiness implements IBasicBusiness<Request<SettingDto>, Response<SettingDto>> {

	private Response<SettingDto> response;
	@Autowired
	private SettingRepository settingRepository;
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

	public SettingBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Setting by using SettingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SettingDto> create(Request<SettingDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Setting-----");

		Response<SettingDto> response = new Response<SettingDto>();
		List<Setting>        items    = new ArrayList<Setting>();
			
		for (SettingDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("valeur", dto.getValeur());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if setting to insert do not exist
			Setting existingEntity = null;

/*
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("setting id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

*/
			// verif unique code in db
			existingEntity = settingRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("setting code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

				Setting entityToSave = null;
			entityToSave = SettingTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setStatusId(StatusEnum.ACTIVE);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Setting> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = settingRepository.saveAll((Iterable<Setting>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("setting", locale));
				response.setHasError(true);
				return response;
			}
			List<SettingDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SettingTransformer.INSTANCE.toLiteDtos(itemsSaved) : SettingTransformer.INSTANCE.toDtos(itemsSaved);

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
			response.setStatus(functionalError.SUCCESS("setting", locale));
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		// System.out.println("----end create Setting-----");
		return response;
	}

	/**
	 * update Setting by using SettingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SettingDto> update(Request<SettingDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Setting-----");

		Response<SettingDto> response = new Response<SettingDto>();
		List<Setting>        items    = new ArrayList<Setting>();
			
		for (SettingDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la setting existe
			Setting entityToSave = null;
			entityToSave = settingRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("setting id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getValeur())) {
				entityToSave.setValeur(dto.getValeur());
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
			List<Setting> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = settingRepository.saveAll((Iterable<Setting>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("setting", locale));
				response.setHasError(true);
				return response;
			}
			List<SettingDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SettingTransformer.INSTANCE.toLiteDtos(itemsSaved) : SettingTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Setting-----");
		return response;
	}

	/**
	 * delete Setting by using SettingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SettingDto> delete(Request<SettingDto> request, Locale locale)  {
		// System.out.println("----begin delete Setting-----");

		Response<SettingDto> response = new Response<SettingDto>();
		List<Setting>        items    = new ArrayList<Setting>();
			
		for (SettingDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la setting existe
			Setting existingEntity = null;

			existingEntity = settingRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("setting -> " + dto.getId(), locale));
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
			settingRepository.saveAll((Iterable<Setting>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Setting-----");
		return response;
	}

	/**
	 * get Setting by using SettingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<SettingDto> getByCriteria(Request<SettingDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Setting-----");

		Response<SettingDto> response = new Response<SettingDto>();
		List<Setting> items 			 = settingRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<SettingDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? SettingTransformer.INSTANCE.toLiteDtos(items) : SettingTransformer.INSTANCE.toDtos(items);

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
			response.setCount(settingRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("setting", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Setting-----");
		return response;
	}

	/**
	 * get full SettingDto by using Setting as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private SettingDto getFullInfos(SettingDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
