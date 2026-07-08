
/*
 * Java dto for entity table personnel 
 * Created on 2026-07-07 ( Time 23:14:25 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto.base;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;




import com.wdyapplications.prime_access.utils.contract.*;
import lombok.Data;

/**
 * DTO customize for table "personnel"
 * 
 * @author Smile Back-End generator
 *
 */

@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _PersonnelDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     nom                  ;
    protected String     prenoms              ;
    protected String     telephone            ;
    protected String     fonction             ;
    protected String     contractant          ;
	protected String     createdAt            ;
    protected Integer    createdBy            ;
	protected String     deletedAt            ;
    protected Boolean    isDeleted            ;
	protected String     updatedAt            ;
    protected Integer    updatedBy            ;
    protected Integer    statusId             ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   nomParam              ;                     
	protected SearchParam<String>   prenomsParam          ;                     
	protected SearchParam<String>   telephoneParam        ;                     
	protected SearchParam<String>   fonctionParam         ;                     
	protected SearchParam<String>   contractantParam      ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<Integer>  createdByParam        ;                     
	protected SearchParam<String>   deletedAtParam        ;                     
	protected SearchParam<Boolean>  isDeletedParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  updatedByParam        ;                     
	protected SearchParam<Integer>  statusIdParam         ;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
