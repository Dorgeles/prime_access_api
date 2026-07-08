

/*
 * Java controller for entity table site 
 * Created on 2026-07-07 ( Time 23:14:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package com.wdyapplications.prime_access.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wdyapplications.prime_access.utils.*;
import com.wdyapplications.prime_access.utils.dto.*;
import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.contract.Request;
import com.wdyapplications.prime_access.utils.contract.Response;
import com.wdyapplications.prime_access.utils.enums.FunctionalityEnum;
import com.wdyapplications.prime_access.business.*;
import com.wdyapplications.prime_access.rest.fact.ControllerFactory;

/**
Controller for table "site"
 * 
 * @author dorgeddy
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/site")
public class SiteController {

	@Autowired
    private ControllerFactory<SiteDto> controllerFactory;
	@Autowired
	private SiteBusiness siteBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SiteDto> create(@RequestBody Request<SiteDto> request) {
    	// System.out.println("start method /site/create");
        Response<SiteDto> response = controllerFactory.create(siteBusiness, request, FunctionalityEnum.CREATE_SITE);
		// System.out.println("end method /site/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SiteDto> update(@RequestBody Request<SiteDto> request) {
    	// System.out.println("start method /site/update");
        Response<SiteDto> response = controllerFactory.update(siteBusiness, request, FunctionalityEnum.UPDATE_SITE);
		// System.out.println("end method /site/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SiteDto> delete(@RequestBody Request<SiteDto> request) {
    	// System.out.println("start method /site/delete");
        Response<SiteDto> response = controllerFactory.delete(siteBusiness, request, FunctionalityEnum.DELETE_SITE);
		// System.out.println("end method /site/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SiteDto> getByCriteria(@RequestBody Request<SiteDto> request) {
    	// System.out.println("start method /site/getByCriteria");
        Response<SiteDto> response = controllerFactory.getByCriteria(siteBusiness, request, FunctionalityEnum.VIEW_SITE);
		// System.out.println("end method /site/getByCriteria");
        return response;
    }
}
