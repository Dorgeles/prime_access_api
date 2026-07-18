

/*
 * Java controller for entity table personnel 
 * Created on 2026-07-07 ( Time 23:14:25 )
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

import java.util.Locale;
import java.util.Map;

/**
Controller for table "personnel"
 * 
 * @author dorgeddy
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/personnel")
public class PersonnelController {

	@Autowired
    private ControllerFactory<PersonnelDto> controllerFactory;
	@Autowired
	private PersonnelBusiness personnelBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PersonnelDto> create(@RequestBody Request<PersonnelDto> request) {
    	// System.out.println("start method /personnel/create");
        Response<PersonnelDto> response = controllerFactory.create(personnelBusiness, request, FunctionalityEnum.CREATE_PERSONNEL);
		// System.out.println("end method /personnel/create");
        return response;
    }

    @RequestMapping(value = "/countPersonnel", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public Response<Map<String, Object>> nbInOutToDay(@RequestBody Request<PersonnelDto> request) {
        // System.out.println("start method /serviceOrder/nbSoByState");
        Response<Map<String, Object>> response =  personnelBusiness.countPersonnel(request, Locale.FRENCH);
        // System.out.println("end method /serviceOrder/nbSoByState");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PersonnelDto> update(@RequestBody Request<PersonnelDto> request) {
    	// System.out.println("start method /personnel/update");
        Response<PersonnelDto> response = controllerFactory.update(personnelBusiness, request, FunctionalityEnum.UPDATE_PERSONNEL);
		// System.out.println("end method /personnel/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PersonnelDto> delete(@RequestBody Request<PersonnelDto> request) {
    	// System.out.println("start method /personnel/delete");
        Response<PersonnelDto> response = controllerFactory.delete(personnelBusiness, request, FunctionalityEnum.DELETE_PERSONNEL);
		// System.out.println("end method /personnel/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PersonnelDto> getByCriteria(@RequestBody Request<PersonnelDto> request) {
    	// System.out.println("start method /personnel/getByCriteria");
        Response<PersonnelDto> response = controllerFactory.getByCriteria(personnelBusiness, request, FunctionalityEnum.VIEW_PERSONNEL);
		// System.out.println("end method /personnel/getByCriteria");
        return response;
    }
}
