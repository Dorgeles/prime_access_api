
/*
 * Created on 2026-07-07 ( Time 23:14:52 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.wdyapplications.prime_access.utils.contract;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;


/**
 * Request
 * 
 * @author dorgeddy
 *
 */


@Data
@JsonInclude(Include.NON_NULL)
public class Request<T> extends RequestBase {
	protected T			data;
	protected List<T>	datas;
}