package com.sandhata.async.models;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Validated
@lombok.Generated
@Data
@ToString
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String county;
	
	private String country;
	
	private String postCode;
	
	private Contact contact;

}
