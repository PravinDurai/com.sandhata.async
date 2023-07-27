package com.sandhata.async.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@lombok.Generated
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AsyncCustomError {

	INVALID_INPUT_DATA("400", "High", "Bad Request", "Invalid input data");

	private String errorCode;

	private String severity;

	private String reason;

	private String description;

}
