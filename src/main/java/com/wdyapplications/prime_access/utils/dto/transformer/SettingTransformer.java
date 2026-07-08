

/*
 * Java transformer for entity table setting 
 * Created on 2026-07-08 ( Time 08:36:55 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.wdyapplications.prime_access.dao.entity.Personnel;
import com.wdyapplications.prime_access.dao.entity.Setting;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.dto.*;


/**
 * TRANSFORMER for table "setting"
 * 
 * @author dorgeddy
 *
 */
@Mapper
public interface SettingTransformer {

	SettingTransformer INSTANCE = Mappers.getMapper(SettingTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
	})
	SettingDto toDto(Setting entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<SettingDto> toDtos(List<Setting> entities) throws ParseException;

    default SettingDto toLiteDto(Setting entity) {
		if (entity == null) {
			return null;
		}
		SettingDto dto = new SettingDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<SettingDto> toLiteDtos(List<Setting> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<SettingDto> dtos = new ArrayList<SettingDto>();
		for (Setting entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.valeur", target="valeur"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.statusId", target="statusId"),
	})
    Setting toEntity(SettingDto dto) throws ParseException;

    //List<Setting> toEntities(List<SettingDto> dtos) throws ParseException;

}
