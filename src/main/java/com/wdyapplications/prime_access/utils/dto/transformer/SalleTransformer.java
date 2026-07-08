

/*
 * Java transformer for entity table salle 
 * Created on 2026-07-07 ( Time 23:14:26 )
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
 * TRANSFORMER for table "salle"
 * 
 * @author dorgeddy
 *
 */
@Mapper
public interface SalleTransformer {

	SalleTransformer INSTANCE = Mappers.getMapper(SalleTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.site.id", target="siteId"),
		@Mapping(source="entity.site.libelle", target="siteLibelle"),
	})
	SalleDto toDto(Salle entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<SalleDto> toDtos(List<Salle> entities) throws ParseException;

    default SalleDto toLiteDto(Salle entity) {
		if (entity == null) {
			return null;
		}
		SalleDto dto = new SalleDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<SalleDto> toLiteDtos(List<Salle> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<SalleDto> dtos = new ArrayList<SalleDto>();
		for (Salle entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.service", target="service"),
		@Mapping(source="dto.capacite", target="capacite"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.statusId", target="statusId"),
		@Mapping(source="site", target="site"),
	})
    Salle toEntity(SalleDto dto, Site site) throws ParseException;

    //List<Salle> toEntities(List<SalleDto> dtos) throws ParseException;

}
