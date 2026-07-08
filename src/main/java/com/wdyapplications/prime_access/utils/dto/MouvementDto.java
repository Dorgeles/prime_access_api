
/*
 * Java dto for entity table mouvement 
 * Created on 2026-07-07 ( Time 23:14:25 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.dto.base._MouvementDto;
import lombok.Data;

/**
 * DTO for table "mouvement"
 *
 * @author dorgeddy
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class MouvementDto extends _MouvementDto{

    private String    statusLibelle               ;
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
