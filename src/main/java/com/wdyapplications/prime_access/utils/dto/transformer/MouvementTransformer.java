

/*
 * Java transformer for entity table mouvement 
 * Created on 2026-07-07 ( Time 23:14:25 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.dto.*;
import com.wdyapplications.prime_access.dao.entity.*;


/**
 * TRANSFORMER for table "mouvement"
 * 
 * @author dorgeddy
 *
 */
@Mapper
public interface MouvementTransformer {

	MouvementTransformer INSTANCE = Mappers.getMapper(MouvementTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.validationDate", dateFormat="YYYY-MM-dd HH:mm:ss",target="validationDate"),
		@Mapping(source="entity.salle.id", target="salleId"),
		@Mapping(source="entity.salle.libelle", target="salleLibelle"),
		@Mapping(source="entity.personnel.id", target="agentSecuriteId"),
		@Mapping(source="entity.personnel.nom", target="personnelNom"),
		@Mapping(source="entity.personnel.prenoms", target="personnelPrenoms"),
	})
	MouvementDto toDto(Mouvement entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<MouvementDto> toDtos(List<Mouvement> entities) throws ParseException;

    default MouvementDto toLiteDto(Mouvement entity) {
		if (entity == null) {
			return null;
		}
		MouvementDto dto = new MouvementDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<MouvementDto> toLiteDtos(List<Mouvement> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<MouvementDto> dtos = new ArrayList<MouvementDto>();
		for (Mouvement entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.typeMouvement", target="typeMouvement"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.statusId", target="statusId"),
		@Mapping(source="dto.latitude", target="latitude"),
		@Mapping(source="dto.longitude", target="longitude"),
		@Mapping(source="dto.validationDate", dateFormat="YYYY-MM-dd HH:mm:ss",target="validationDate"),
		@Mapping(source="personnel2", target="personnel2"),
		@Mapping(source="salle", target="salle"),
		@Mapping(source="personnel", target="personnel"),
	})
    Mouvement toEntity(MouvementDto dto, Personnel personnel2, Salle salle, Personnel personnel) throws ParseException;

    //List<Mouvement> toEntities(List<MouvementDto> dtos) throws ParseException;

}
