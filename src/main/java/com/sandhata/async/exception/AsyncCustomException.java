package com.sandhata.async.exception;

import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Generated
@Data
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
public class AsyncCustomException extends RuntimeException {

	@JsonProperty("Transaction Id")
	private String transactionId;

	@JsonProperty("Error Code")
	private String errorCode;

	@JsonProperty("Severity")
	private String severity;

	@JsonProperty("Error Type")
	private String errorType;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Timestamp")
	private String timeStamp;

	public AsyncCustomException(ErrorResponse errorResponse) throws ParseException {
		this.transactionId = errorResponse.getTransactionId();
		this.errorCode = errorResponse.getErrorCode();
		this.severity = errorResponse.getSeverity();
		this.errorType = errorResponse.getErrorType();
		this.reason = errorResponse.getReason();
		this.description = errorResponse.getDescription();
		this.timeStamp = errorResponse.getTimeStamp();
	}

}
