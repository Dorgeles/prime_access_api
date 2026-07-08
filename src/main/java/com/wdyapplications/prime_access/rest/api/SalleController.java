

/*
 * Java controller for entity table salle 
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
Controller for table "salle"
 * 
 * @author dorgeddy
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/salle")
public class SalleController {

	@Autowired
    private ControllerFactory<SalleDto> controllerFactory;
	@Autowired
	private SalleBusiness salleBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SalleDto> create(@RequestBody Request<SalleDto> request) {
    	// System.out.println("start method /salle/create");
        Response<SalleDto> response = controllerFactory.create(salleBusiness, request, FunctionalityEnum.CREATE_SALLE);
		// System.out.println("end method /salle/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SalleDto> update(@RequestBody Request<SalleDto> request) {
    	// System.out.println("start method /salle/update");
        Response<SalleDto> response = controllerFactory.update(salleBusiness, request, FunctionalityEnum.UPDATE_SALLE);
		// System.out.println("end method /salle/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SalleDto> delete(@RequestBody Request<SalleDto> request) {
    	// System.out.println("start method /salle/delete");
        Response<SalleDto> response = controllerFactory.delete(salleBusiness, request, FunctionalityEnum.DELETE_SALLE);
		// System.out.println("end method /salle/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SalleDto> getByCriteria(@RequestBody Request<SalleDto> request) {
    	// System.out.println("start method /salle/getByCriteria");
        Response<SalleDto> response = controllerFactory.getByCriteria(salleBusiness, request, FunctionalityEnum.VIEW_SALLE);
		// System.out.println("end method /salle/getByCriteria");
        return response;
    }
}
