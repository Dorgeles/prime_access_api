
/*
 * Created on 2026-07-07 ( Time 23:14:52 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package com.wdyapplications.prime_access.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;


public class RestInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(RestInterceptor.class);
	private static String	defaultTenant	= "null";

	private static String defaultLanguage = "fr";

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		String langValue = req.getHeader("lang");

		if (langValue != null) {
			req.setAttribute("CURRENT_LANGUAGE_IDENTIFIER", langValue);
		} else {
			req.setAttribute("CURRENT_LANGUAGE_IDENTIFIER", defaultLanguage);
		}
		return true;
	}
}

