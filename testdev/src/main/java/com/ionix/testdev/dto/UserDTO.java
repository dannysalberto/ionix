package com.ionix.testdev.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.ionix.testdev.common.CommonConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotEmpty(message = CommonConstants.NAME_FIELD + CommonConstants.NOT_EMPTY_NOT_NULL)
	private String name;
	
	@NotEmpty(message = CommonConstants.EMAIL_FIELD + CommonConstants.NOT_EMPTY_NOT_NULL)
	@Email(message = CommonConstants.EMAIL_FIELD + CommonConstants.NOT_EMPTY_NOT_NULL)
	private String email;
	
	@NotEmpty(message = CommonConstants.PHONE_FIELD + CommonConstants.NOT_EMPTY_NOT_NULL)
	private String phone;
	

}
