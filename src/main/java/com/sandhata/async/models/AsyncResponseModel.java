package com.sandhata.async.models;

import java.util.List;

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
public class AsyncResponseModel {

	private String empId;

	private String firstName;

	private String lastName;
	
	private String fullName;

	private Address address;

	private List<String> skillSet;

}
