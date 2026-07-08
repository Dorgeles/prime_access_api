
/*
 * Created on 2026-07-07 ( Time 23:14:52 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.contract;


import com.wdyapplications.prime_access.utils.Status;
import lombok.Data;

/**
 * Response Base
 * 
 * @author dorgeddy
 *
 */

@Data
public class ResponseBase {

	protected Status	status = new Status();
	protected boolean	hasError;
	protected String	sessionUser;
	protected Long		count;
}
