

/*
 * Java transformer for entity table site 
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
 * TRANSFORMER for table "site"
 * 
 * @author dorgeddy
 *
 */
@Mapper
public interface SiteTransformer {

	SiteTransformer INSTANCE = Mappers.getMapper(SiteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
	})
	SiteDto toDto(Site entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<SiteDto> toDtos(List<Site> entities) throws ParseException;

    default SiteDto toLiteDto(Site entity) {
		if (entity == null) {
			return null;
		}
		SiteDto dto = new SiteDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<SiteDto> toLiteDtos(List<Site> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<SiteDto> dtos = new ArrayList<SiteDto>();
		for (Site entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.adresse", target="adresse"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.statusId", target="statusId"),
		@Mapping(source="dto.latitude", target="latitude"),
		@Mapping(source="dto.longitude", target="longitude"),
	})
    Site toEntity(SiteDto dto) throws ParseException;

    //List<Site> toEntities(List<SiteDto> dtos) throws ParseException;

}
