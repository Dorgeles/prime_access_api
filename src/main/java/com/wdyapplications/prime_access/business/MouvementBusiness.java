                                                                    																
/*
 * Java business for entity table mouvement 
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

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
BUSINESS for table "mouvement"
 * 
 * @author dorgeddy
 *
 */

@Component
public class MouvementBusiness implements IBasicBusiness<Request<MouvementDto>, Response<MouvementDto>> {

	private Response<MouvementDto> response;
	@Autowired
	private MouvementRepository mouvementRepository;
	@Autowired
	private PersonnelRepository personnel2Repository;
	@Autowired
	private SalleRepository salleRepository;
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

	public MouvementBusiness() {
		dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTimeFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * create Mouvement by using MouvementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MouvementDto> create(Request<MouvementDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin create Mouvement-----");

		Response<MouvementDto> response = new Response<MouvementDto>();
		List<Mouvement>        items    = new ArrayList<Mouvement>();
			
		for (MouvementDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("latitude", dto.getLatitude());
			fieldsToVerify.put("longitude", dto.getLongitude());
			fieldsToVerify.put("personnelId", dto.getPersonnelId());
			fieldsToVerify.put("salleId", dto.getSalleId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if personnel2 exist
			Personnel existingPersonnel2 = null;
			if (dto.getPersonnelId() != null && dto.getPersonnelId() > 0){
				existingPersonnel2 = personnel2Repository.findOne(dto.getPersonnelId(), false);
				if (existingPersonnel2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("personnel2 personnelId -> " + dto.getPersonnelId(), locale));
					response.setHasError(true);
					return response;
				}
			}
            if(existingPersonnel2 != null && existingPersonnel2.getStatusId() == StatusEnum.EN_COURS) {
				Status status = new Status();
				status.setMessage("Votre compte est en cours d'activation. Merci de bien vouloir contacter votre responsable.");
				status.setCode("700");
				response.setStatus(status);
				response.setHasError(true);
				return response;
			}
			// Verify if salle exist
			Salle existingSalle = null;
			if (dto.getSalleId() != null && dto.getSalleId() > 0){
				existingSalle = salleRepository.findOne(dto.getSalleId(), false);
				if (existingSalle == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("salle salleId -> " + dto.getSalleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// vérification du fait que le mouvement soit dans un rayon de 50 mettres autour du site
			Boolean isInPerimeter = Utilities.estDansRayon(
					existingSalle.getSite().getLatitude().doubleValue(),
					existingSalle.getSite().getLongitude().doubleValue(),
					dto.getLatitude().doubleValue(),
					dto.getLongitude().doubleValue(),
					50
			);

			Mouvement entityToSave = null;
			entityToSave = MouvementTransformer.INSTANCE.toEntity(dto, existingPersonnel2, existingSalle, null);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			if(!isInPerimeter) {
				entityToSave.setStatusId(StatusEnum.REFUSER);
			} else {
				entityToSave.setStatusId(StatusEnum.EN_COURS);
			}
			entityToSave.setTypeMouvement(determinerTypeMouvement(entityToSave.getPersonnel2().getId(), mouvementRepository));
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Mouvement> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = mouvementRepository.saveAll((Iterable<Mouvement>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("mouvement", locale));
				response.setHasError(true);
				return response;
			}
			List<MouvementDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MouvementTransformer.INSTANCE.toLiteDtos(itemsSaved) : MouvementTransformer.INSTANCE.toDtos(itemsSaved);

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
			response.setStatus(functionalError.SUCCESS("mouvement", locale));
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		// System.out.println("----end create Mouvement-----");
		return response;
	}


	public Response<Map<String, Object>> nbMouvement(Request<MouvementDto> request, Locale locale)  throws ParseException {
		Response<Map<String, Object>> response = new Response<Map<String, Object>>();
		try {
			MouvementDto dto = request.getData();
			String dateD = dto.getDateDebut().replace(" ", "T");
			String dateF = dto.getDateFin().replace(" ", "T");
			List<Map<String, Object>> statistiques = obtenirStatistiques(
					LocalDateTime.parse(dateD),
					LocalDateTime.parse(dateF),
                    Objects.equals(dto.getGranularite(), "hour") ? Granularite.HEURE: Objects.equals(dto.getGranularite(), "day") ? Granularite.JOUR : Objects.equals(dto.getGranularite(), "week") ? Granularite.SEMAINE : Objects.equals(dto.getGranularite(), "month") ? Granularite.MOIS : Granularite.ANNEE
			);
			response.setItems(statistiques);
			response.setStatus(functionalError.SUCCESS("statistiques", locale));
			response.setHasError(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	//Request<MouvementDto> request

	public List<Map<String, Object>> obtenirStatistiques(LocalDateTime debut,
	                                                         LocalDateTime fin,
	                                                         Granularite granularite) {
		if (debut == null || fin == null) {
			throw new IllegalArgumentException("Les dates de début et de fin sont obligatoires");
		}
		if (debut.isAfter(fin)) {
			throw new IllegalArgumentException("La date de début doit précéder la date de fin");
		}
		if (granularite == null) {
			throw new IllegalArgumentException("La granularité est obligatoire");
		}

		List<Object[]> lignesBrutes = mouvementRepository.statistiquesBrutes(
				granularite.getUnitePostgres(), debut, fin);
		String[] headers = {"id", "name", "age", "city"};

		return lignesBrutes.stream()
				.map(row -> IntStream.range(0, Math.min(headers.length, row.length))
						.boxed()
						.collect(Collectors.toMap(
								i -> headers[i],
								i -> row[i]
						)))
				.collect(Collectors.toList());
	}

	public String determinerTypeMouvement(int idPersonnel, MouvementRepository repo) {
		Optional<Mouvement> dernier = repo.findDernierMouvement(idPersonnel);

		if (dernier.isEmpty() || dernier.get().getTypeMouvement() == TypeMouvement.SORTIR) {
			return TypeMouvement.ENTRER;
		}
		return TypeMouvement.SORTIR;
	}

	/**
	 * update Mouvement by using MouvementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MouvementDto> update(Request<MouvementDto> request, Locale locale)  throws ParseException {
		// System.out.println("----begin update Mouvement-----");

		Response<MouvementDto> response = new Response<MouvementDto>();
		List<Mouvement>        items    = new ArrayList<Mouvement>();
			
		for (MouvementDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la mouvement existe
			Mouvement entityToSave = null;
			entityToSave = mouvementRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("mouvement id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if personnel2 exist
			if (dto.getPersonnelId() != null && dto.getPersonnelId() > 0){
				Personnel existingPersonnel2 = personnel2Repository.findOne(dto.getPersonnelId(), false);
				if (existingPersonnel2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("personnel2 personnelId -> " + dto.getPersonnelId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setPersonnel2(existingPersonnel2);
			}
			// Verify if salle exist
			if (dto.getSalleId() != null && dto.getSalleId() > 0){
				Salle existingSalle = salleRepository.findOne(dto.getSalleId(), false);
				if (existingSalle == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("salle salleId -> " + dto.getSalleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setSalle(existingSalle);
			}
			// Verify if personnel exist
			if (dto.getAgentSecuriteId() != null && dto.getAgentSecuriteId() > 0){
				Personnel existingPersonnel = personnelRepository.findOne(dto.getAgentSecuriteId(), false);
				if (existingPersonnel == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("personnel agentSecuriteId -> " + dto.getAgentSecuriteId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setPersonnel(existingPersonnel);
			}
			if (Utilities.notBlank(dto.getTypeMouvement())) {
				entityToSave.setTypeMouvement(dto.getTypeMouvement());
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
			if ( Utilities.isValid(dto.getLatitude())) {
				entityToSave.setLatitude(dto.getLatitude());
			}
			if (Utilities.isValid(dto.getLongitude())) {
				entityToSave.setLongitude(dto.getLongitude());
			}
			if (Utilities.notBlank(dto.getValidationDate())) {
				entityToSave.setValidationDate(dateFormat.parse(dto.getValidationDate()));
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Mouvement> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = mouvementRepository.saveAll((Iterable<Mouvement>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("mouvement", locale));
				response.setHasError(true);
				return response;
			}
			List<MouvementDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MouvementTransformer.INSTANCE.toLiteDtos(itemsSaved) : MouvementTransformer.INSTANCE.toDtos(itemsSaved);

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

		// System.out.println("----end update Mouvement-----");
		return response;
	}

	/**
	 * delete Mouvement by using MouvementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MouvementDto> delete(Request<MouvementDto> request, Locale locale)  {
		// System.out.println("----begin delete Mouvement-----");

		Response<MouvementDto> response = new Response<MouvementDto>();
		List<Mouvement>        items    = new ArrayList<Mouvement>();
			
		for (MouvementDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la mouvement existe
			Mouvement existingEntity = null;

			existingEntity = mouvementRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("mouvement -> " + dto.getId(), locale));
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
			mouvementRepository.saveAll((Iterable<Mouvement>) items);

			response.setHasError(false);
		}

		// System.out.println("----end delete Mouvement-----");
		return response;
	}

	/**
	 * get Mouvement by using MouvementDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MouvementDto> getByCriteria(Request<MouvementDto> request, Locale locale)  throws Exception {
		// System.out.println("----begin get Mouvement-----");

		Response<MouvementDto> response = new Response<MouvementDto>();
		List<Mouvement> items 			 = mouvementRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<MouvementDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MouvementTransformer.INSTANCE.toLiteDtos(items) : MouvementTransformer.INSTANCE.toDtos(items);

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
			response.setCount(mouvementRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("mouvement", locale));
			response.setHasError(false);
			return response;
		}

		// System.out.println("----end get Mouvement-----");
		return response;
	}

	/**
	 * get full MouvementDto by using Mouvement as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private MouvementDto getFullInfos(MouvementDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		dto.setDataPersonnel(dto.getPersonnelId() != null && dto.getPersonnelId() > 0? PersonnelTransformer.INSTANCE.toDto(personnelRepository.findOne(dto.getPersonnelId(), false)) : null);
		dto.setDataSalle(dto.getSalleId() != null && dto.getSalleId() > 0? SalleTransformer.INSTANCE.toLiteDto(salleRepository.findOne(dto.getSalleId(), false)) : null);

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
