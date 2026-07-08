

/*
 * Java controller for entity table setting 
 * Created on 2026-07-08 ( Time 08:36:55 )
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
Controller for table "setting"
 * 
 * @author dorgeddy
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/setting")
public class SettingController {

	@Autowired
    private ControllerFactory<SettingDto> controllerFactory;
	@Autowired
	private SettingBusiness settingBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SettingDto> create(@RequestBody Request<SettingDto> request) {
    	// System.out.println("start method /setting/create");
        Response<SettingDto> response = controllerFactory.create(settingBusiness, request, FunctionalityEnum.CREATE_SETTING);
		// System.out.println("end method /setting/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SettingDto> update(@RequestBody Request<SettingDto> request) {
    	// System.out.println("start method /setting/update");
        Response<SettingDto> response = controllerFactory.update(settingBusiness, request, FunctionalityEnum.UPDATE_SETTING);
		// System.out.println("end method /setting/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SettingDto> delete(@RequestBody Request<SettingDto> request) {
    	// System.out.println("start method /setting/delete");
        Response<SettingDto> response = controllerFactory.delete(settingBusiness, request, FunctionalityEnum.DELETE_SETTING);
		// System.out.println("end method /setting/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<SettingDto> getByCriteria(@RequestBody Request<SettingDto> request) {
    	// System.out.println("start method /setting/getByCriteria");
        Response<SettingDto> response = controllerFactory.getByCriteria(settingBusiness, request, FunctionalityEnum.VIEW_SETTING);
		// System.out.println("end method /setting/getByCriteria");
        return response;
    }
}
