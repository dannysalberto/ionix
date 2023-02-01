package com.ionix.testdev.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralResponse {
	
	private int code;
	private String message;

}
