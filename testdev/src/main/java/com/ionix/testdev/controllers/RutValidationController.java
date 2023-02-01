package com.ionix.testdev.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ionix.testdev.business.RutValidationBusinnes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rutvalidation")
@Tag(name= "rutvalidation", description =  "rutvalidation" )
@SecurityRequirement(name = "basicAuth")
public class RutValidationController {
	
	@Autowired
	RutValidationBusinnes rutValidationBusinnes;
	
			
	@Operation(summary = "Get validation RUT")
	@PostMapping(value="valid/{rut}" , produces = {"application/json"} )
    public ResponseEntity<?> save(@PathVariable("rut") String  rut) throws Exception  {
        return rutValidationBusinnes.getRUTValidate(rut);
    }
	

}
