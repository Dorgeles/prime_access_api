
/*
 * Java dto for entity table utilisateur 
 * Created on 2026-07-07 ( Time 23:14:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto.base;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;




import com.wdyapplications.prime_access.utils.contract.*;
import lombok.Data;

/**
 * DTO customize for table "utilisateur"
 * 
 * @author Smile Back-End generator
 *
 */

@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _UtilisateurDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     login                ;
    protected String     password             ;
    protected String     role                 ;
    protected Integer    personnelId          ;
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
	//protected Integer    personnel;
	protected String personnelNom;
	protected String personnelPrenoms;
	protected String personnelTelephone;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   loginParam            ;                     
	protected SearchParam<String>   passwordParam         ;                     
	protected SearchParam<String>   roleParam             ;                     
	protected SearchParam<Integer>  personnelIdParam      ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<Integer>  createdByParam        ;                     
	protected SearchParam<String>   deletedAtParam        ;                     
	protected SearchParam<Boolean>  isDeletedParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  updatedByParam        ;                     
	protected SearchParam<Integer>  statusIdParam         ;                     
	protected SearchParam<Integer>  personnelParam        ;                     
	protected SearchParam<String>   personnelNomParam     ;                     
	protected SearchParam<String>   personnelPrenomsParam ;                     
	protected SearchParam<String>   personnelTelephoneParam;                     

	// order param
	protected String orderField;
	protected String orderDirection;




}
