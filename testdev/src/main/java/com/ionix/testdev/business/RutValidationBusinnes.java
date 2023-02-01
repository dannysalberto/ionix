package com.ionix.testdev.business;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.ionix.testdev.dto.ResponseRUTValidation;
import com.ionix.testdev.responses.ResultResponse;
import com.ionix.testdev.responses.RutResponse;
import com.ionix.testdev.services.CipherService;
import com.ionix.testdev.utils.ConvertDTO;

@Component
public class RutValidationBusinnes {

	@Autowired
	CipherService cipherService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ConvertDTO convertDTO;
	
	private UriComponentsBuilder uri;
	
	@Value("${rut.service.path}")
	private String urlRutValidation;
	
	
	@Value("${rut.service.xapikey}")
	private String xApiKey;

	
	public ResponseEntity<?> getRUTValidate(String rut) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		long startTimeTry = System.currentTimeMillis();	
		String rutCipher = cipherService.CipherKey(rut);
		ResponseEntity<?> resp = serviceConsume(rutCipher);
		long endTimeConn = System.currentTimeMillis() - startTimeTry;

		if (resp.getStatusCode().value() == HttpStatus.SC_OK) {
			
			ResponseRUTValidation result = new Gson().fromJson(resp.getBody().toString(), ResponseRUTValidation.class);
			
			RutResponse response = mapperResponse(endTimeConn, result);
			
			return new ResponseEntity<RutResponse>(response,null,HttpStatus.SC_OK);		

		}
		return resp;		
	}


	private RutResponse mapperResponse(long endTimeConn, ResponseRUTValidation result) {
		RutResponse response = new RutResponse();
		response.setResponseCode(result.getResponseCode());
		response.setDescription(result.getDescription());
		response.setElapsedTime((int) endTimeConn);
		
		ResultResponse resultResp = new ResultResponse();
		resultResp.setRegisterCount(result.getResult().getItems().size());
		response.setResult(resultResp);

		return response;
	}


	private ResponseEntity<?> serviceConsume(String rut) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-API-Key", xApiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		this.uri = UriComponentsBuilder.fromHttpUrl(urlRutValidation + rut);
		ResponseEntity<?> resp = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, entity, String.class);
		return resp;
	}
	
	
	
}
