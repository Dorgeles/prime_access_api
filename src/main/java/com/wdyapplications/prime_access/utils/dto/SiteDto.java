
/*
 * Java dto for entity table site 
 * Created on 2026-07-07 ( Time 23:14:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.dto.base._SiteDto;
import lombok.Data;

/**
 * DTO for table "site"
 *
 * @author dorgeddy
 */

@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class SiteDto extends _SiteDto{

    private String    statusLibelle               ;
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
