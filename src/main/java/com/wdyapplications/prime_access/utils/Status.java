
/*
 * Created on 2026-07-07 ( Time 23:14:52 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;


/**
 * Status
 * 
 * @author dorgeddy
 *
 */


@Data
@JsonInclude(Include.NON_NULL)
public class Status {
	private String	code;
	private String	message;
}
