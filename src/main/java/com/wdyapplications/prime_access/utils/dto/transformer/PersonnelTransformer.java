

/*
 * Java transformer for entity table personnel 
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
 * TRANSFORMER for table "personnel"
 * 
 * @author dorgeddy
 *
 */
@Mapper
public interface PersonnelTransformer {

	PersonnelTransformer INSTANCE = Mappers.getMapper(PersonnelTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
	})
	PersonnelDto toDto(Personnel entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PersonnelDto> toDtos(List<Personnel> entities) throws ParseException;

    default PersonnelDto toLiteDto(Personnel entity) {
		if (entity == null) {
			return null;
		}
		PersonnelDto dto = new PersonnelDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenoms( entity.getPrenoms() );
		dto.setImageUrl( entity.getImageUrl() );
		return dto;
    }

	default List<PersonnelDto> toLiteDtos(List<Personnel> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PersonnelDto> dtos = new ArrayList<PersonnelDto>();
		for (Personnel entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenoms", target="prenoms"),
		@Mapping(source="dto.telephone", target="telephone"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.fonction", target="fonction"),
		@Mapping(source="dto.imageUrl", target="imageUrl"),
		@Mapping(source="dto.contractant", target="contractant"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.statusId", target="statusId"),
	})
    Personnel toEntity(PersonnelDto dto) throws ParseException;

    //List<Personnel> toEntities(List<PersonnelDto> dtos) throws ParseException;

}
