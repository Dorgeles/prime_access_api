/*
 * Created on 2026-07-07 ( Time 23:14:52 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.contract;


import lombok.Data;

/**
 * Request Base
 * 
 * @author dorgeddy
 *
 */

@Data
public class RequestBase {
	protected String		sessionUser;
	protected Integer		size;
	protected Integer		index;
	protected String		lang;
	protected Boolean		isAnd;
	protected Integer		user;
	protected Boolean 		isSimpleLoading;
    protected String        action;
}