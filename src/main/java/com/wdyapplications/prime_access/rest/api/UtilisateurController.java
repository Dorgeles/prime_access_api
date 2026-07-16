

/*
 * Java controller for entity table utilisateur 
 * Created on 2026-07-07 ( Time 23:14:26 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package com.wdyapplications.prime_access.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wdyapplications.prime_access.utils.*;
import com.wdyapplications.prime_access.utils.dto.*;
import com.wdyapplications.prime_access.utils.contract.*;
import com.wdyapplications.prime_access.utils.contract.Request;
import com.wdyapplications.prime_access.utils.contract.Response;
import com.wdyapplications.prime_access.utils.enums.FunctionalityEnum;
import com.wdyapplications.prime_access.business.*;
import com.wdyapplications.prime_access.rest.fact.ControllerFactory;

import java.text.ParseException;
import java.util.Locale;

/**
Controller for table "utilisateur"
 * 
 * @author dorgeddy
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/utilisateur")
public class UtilisateurController {

	@Autowired
    private ControllerFactory<UtilisateurDto> controllerFactory;
	@Autowired
	private UtilisateurBusiness utilisateurBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> create(@RequestBody Request<UtilisateurDto> request) {
    	// System.out.println("start method /utilisateur/create");
        Response<UtilisateurDto> response = controllerFactory.create(utilisateurBusiness, request, FunctionalityEnum.CREATE_UTILISATEUR);
		// System.out.println("end method /utilisateur/create");
        return response;
    }

    @RequestMapping(value="/login",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> login(@RequestBody Request<UtilisateurDto> request) throws ParseException {
        // System.out.println("start method /utilisateur/update");
        Response<UtilisateurDto> response = utilisateurBusiness.login(request, Locale.FRENCH);
        // System.out.println("end method /utilisateur/update");
        return response;
    }

    @GetMapping(value="/test",produces={"application/json"})
    public ResponseEntity test(@RequestBody Request<UtilisateurDto> request) throws ParseException {
        // System.out.println("start method /utilisateur/update");
        utilisateurBusiness.testEmail();
        // System.out.println("end method /utilisateur/update");
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/activerUtilisateurPersonnel",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> activeUtilisateurPersonnel(@RequestBody Request<UtilisateurDto> request) throws ParseException {
        // System.out.println("start method /utilisateur/update");
        Response<UtilisateurDto> response = utilisateurBusiness.activeUtilisateurPersonnel(request, Locale.FRENCH);
        // System.out.println("end method /utilisateur/update");
        return response;
    }

    @RequestMapping(value="/sendResetPassword",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> sendResetPassword(@RequestBody Request<UtilisateurDto> request) throws ParseException {
        // System.out.println("start method /utilisateur/update");
        Response<UtilisateurDto> response = utilisateurBusiness.sendResetPassword(request, Locale.FRENCH);
        // System.out.println("end method /utilisateur/update");
        return response;
    }

    @RequestMapping(value="/resetPassword",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> resetPassword(@RequestBody Request<UtilisateurDto> request) throws ParseException {
        // System.out.println("start method /utilisateur/update");
        Response<UtilisateurDto> response = utilisateurBusiness.resetPassword(request, Locale.FRENCH);
        // System.out.println("end method /utilisateur/update");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> update(@RequestBody Request<UtilisateurDto> request) {
    	// System.out.println("start method /utilisateur/update");
        Response<UtilisateurDto> response = controllerFactory.update(utilisateurBusiness, request, FunctionalityEnum.UPDATE_UTILISATEUR);
		// System.out.println("end method /utilisateur/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> delete(@RequestBody Request<UtilisateurDto> request) {
    	// System.out.println("start method /utilisateur/delete");
        Response<UtilisateurDto> response = controllerFactory.delete(utilisateurBusiness, request, FunctionalityEnum.DELETE_UTILISATEUR);
		// System.out.println("end method /utilisateur/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UtilisateurDto> getByCriteria(@RequestBody Request<UtilisateurDto> request) {
    	// System.out.println("start method /utilisateur/getByCriteria");
        Response<UtilisateurDto> response = controllerFactory.getByCriteria(utilisateurBusiness, request, FunctionalityEnum.VIEW_UTILISATEUR);
		// System.out.println("end method /utilisateur/getByCriteria");
        return response;
    }
}
