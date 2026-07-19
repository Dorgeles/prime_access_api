

/*
 * Java controller for entity table mouvement 
 * Created on 2026-07-07 ( Time 23:14:25 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package com.wdyapplications.prime_access.rest.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
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

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
Controller for table "mouvement"
 * 
 * @author dorgeddy
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/mouvement")
public class MouvementController {
    @Autowired
    private FunctionalError functionalError;
	@Autowired
    private ControllerFactory<MouvementDto> controllerFactory;
	@Autowired
	private MouvementBusiness mouvementBusiness;
    @Autowired
    private HttpServletRequest requestBasic;
	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MouvementDto> create(@RequestBody Request<MouvementDto> request) {
    	// System.out.println("start method /mouvement/create");
        Response<MouvementDto> response = controllerFactory.create(mouvementBusiness, request, FunctionalityEnum.CREATE_MOUVEMENT);
		// System.out.println("end method /mouvement/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MouvementDto> update(@RequestBody Request<MouvementDto> request) {
    	// System.out.println("start method /mouvement/update");
        Response<MouvementDto> response = controllerFactory.update(mouvementBusiness, request, FunctionalityEnum.UPDATE_MOUVEMENT);
		// System.out.println("end method /mouvement/update");
        return response;
    }

    @RequestMapping(value="/nbMouvementByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<Map<String, Object>> nbMouvementByCriteria(@RequestBody Request<MouvementDto> request) throws ParseException {
        // System.out.println("start method /mouvement/update");
        Response<Map<String, Object>> response = mouvementBusiness.nbMouvement(request, Locale.FRENCH);
        // System.out.println("end method /mouvement/update");
        return response;
    }
    @RequestMapping(value = "/nbInOutByGranularite", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public Response<Map<String, Object>> nbInOutByGranularite(@RequestBody Request<MouvementDto> request) {
        // System.out.println("start method /serviceOrder/nbSoByState");
        Response<Map<String, Object>> response =  mouvementBusiness.nbInOutByGranularite(request, Locale.FRENCH);
        // System.out.println("end method /serviceOrder/nbSoByState");
        return response;
    }

    @RequestMapping(value = "/nbInOutToDay", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public Response<Map<String, Object>> nbInOutToDay(@RequestBody Request<MouvementDto> request) {
        // System.out.println("start method /serviceOrder/nbSoByState");
        Response<Map<String, Object>> response =  mouvementBusiness.nbInOutToDay(request, Locale.FRENCH);
        // System.out.println("end method /serviceOrder/nbSoByState");
        return response;
    }
    //nbPersonnelPresent
    @RequestMapping(value = "/nbPersonnelPresent", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public Response<Map<String, Object>> nbPersonnelPresent(@RequestBody Request<MouvementDto> request) {
        // System.out.println("start method /serviceOrder/nbSoByState");
        Response<Map<String, Object>> response =  mouvementBusiness.nbPersonnelPresent(request, Locale.FRENCH);
        // System.out.println("end method /serviceOrder/nbSoByState");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MouvementDto> delete(@RequestBody Request<MouvementDto> request) {
    	// System.out.println("start method /mouvement/delete");
        Response<MouvementDto> response = controllerFactory.delete(mouvementBusiness, request, FunctionalityEnum.DELETE_MOUVEMENT);
		// System.out.println("end method /mouvement/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MouvementDto> getByCriteria(@RequestBody Request<MouvementDto> request) {
    	// System.out.println("start method /mouvement/getByCriteria");
        Response<MouvementDto> response = controllerFactory.getByCriteria(mouvementBusiness, request, FunctionalityEnum.VIEW_MOUVEMENT);
		// System.out.println("end method /mouvement/getByCriteria");
        return response;
    }
}
