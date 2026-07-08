
/*
 * Java dto for entity table mouvement 
 * Created on 2026-07-07 ( Time 23:14:25 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto.base;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;




import com.wdyapplications.prime_access.utils.contract.*;
import lombok.Data;

/**
 * DTO customize for table "mouvement"
 * 
 * @author Smile Back-End generator
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _MouvementDto implements Cloneable {

    protected Integer    id                   ; // Primary Key

    protected String     typeMouvement        ;
	protected String     createdAt            ;
    protected Integer    createdBy            ;
	protected String     deletedAt            ;
    protected Boolean    isDeleted            ;
	protected String     updatedAt            ;
    protected Integer    updatedBy            ;
    protected Integer    statusId             ;
    protected BigDecimal latitude             ;
    protected BigDecimal longitude            ;
	protected String     validationDate       ;
    protected Integer    personnelId          ;
    protected Integer    salleId              ;
    protected Integer    agentSecuriteId      ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//protected Integer    personnel;
	protected String personnelNom;
	protected String personnelPrenoms;
	protected String personnelTelephone;
	//protected Integer    salle;
	protected String salleLibelle;

	// Search param
	protected SearchParam<Integer>  idParam               ;                     
	protected SearchParam<String>   typeMouvementParam    ;                     
	protected SearchParam<String>   createdAtParam        ;                     
	protected SearchParam<Integer>  createdByParam        ;                     
	protected SearchParam<String>   deletedAtParam        ;                     
	protected SearchParam<Boolean>  isDeletedParam        ;                     
	protected SearchParam<String>   updatedAtParam        ;                     
	protected SearchParam<Integer>  updatedByParam        ;                     
	protected SearchParam<Integer>  statusIdParam         ;                     
	protected SearchParam<BigDecimal>latitudeParam         ;                     
	protected SearchParam<BigDecimal>longitudeParam        ;                     
	protected SearchParam<String>   validationDateParam   ;                     
	protected SearchParam<Integer>  personnelIdParam      ;                     
	protected SearchParam<Integer>  salleIdParam          ;                     
	protected SearchParam<Integer>  agentSecuriteIdParam  ;                     
	protected SearchParam<Integer>  personnelParam        ;                     
	protected SearchParam<String>   personnelNomParam     ;                     
	protected SearchParam<String>   personnelPrenomsParam ;                     
	protected SearchParam<String>   personnelTelephoneParam;                     
	protected SearchParam<Integer>  salleParam            ;                     
	protected SearchParam<String>   salleLibelleParam     ;

	// order param
	protected String orderField;
	protected String orderDirection;




}
