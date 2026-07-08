

/*
 * Java transformer for entity table utilisateur 
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
 * TRANSFORMER for table "utilisateur"
 * 
 * @author dorgeddy
 *
 */
@Mapper
public interface UtilisateurTransformer {

	UtilisateurTransformer INSTANCE = Mappers.getMapper(UtilisateurTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="entity.personnel.id", target="personnelId"),
		@Mapping(source="entity.personnel.nom", target="personnelNom"),
		@Mapping(source="entity.personnel.prenoms", target="personnelPrenoms"),
	})
	UtilisateurDto toDto(Utilisateur entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UtilisateurDto> toDtos(List<Utilisateur> entities) throws ParseException;

    default UtilisateurDto toLiteDto(Utilisateur entity) {
		if (entity == null) {
			return null;
		}
		UtilisateurDto dto = new UtilisateurDto();
		dto.setId( entity.getId() );
		dto.setLogin( entity.getLogin() );
		return dto;
    }

	default List<UtilisateurDto> toLiteDtos(List<Utilisateur> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UtilisateurDto> dtos = new ArrayList<UtilisateurDto>();
		for (Utilisateur entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.login", target="login"),
		@Mapping(source="dto.password", target="password"),
		@Mapping(source="dto.role", target="role"),
		@Mapping(source="dto.createdAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedAt", dateFormat="YYYY-MM-dd HH:mm:ss",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.statusId", target="statusId"),
		@Mapping(source="personnel", target="personnel"),
	})
    Utilisateur toEntity(UtilisateurDto dto, Personnel personnel) throws ParseException;

    //List<Utilisateur> toEntities(List<UtilisateurDto> dtos) throws ParseException;

}
