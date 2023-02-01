package com.ionix.testdev.responses;

import lombok.Data;

@Data
public class RutResponse {

	private int responseCode;
	private String description;
	private int elapsedTime;

	private ResultResponse result;
    
}


