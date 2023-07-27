package com.sandhata.async.exception;

import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sandhata.async.util.AsyncUtil;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Generated
@Data
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

	@JsonProperty("Transaction Id")
	private String transactionId;

	@JsonProperty("Error Code")
	private String errorCode;

	@JsonProperty("Severity")
	private String severity;

	@JsonProperty("Error Type")
	private String errorType;

	@JsonProperty("Reason")
	private String reason;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Timestamp")
	private String timeStamp;

	@JsonProperty("Class Name")
	private String className;

	@JsonProperty("Method Name")
	private String methodName;

	public ErrorResponse(AsyncCustomError customErrors, String className, String methodName, String transactionId,
			String errorType) throws ParseException {
		this.transactionId = transactionId;
		this.errorCode = customErrors.getErrorCode();
		this.severity = customErrors.getSeverity();
		this.errorType = errorType;
		this.reason = customErrors.getReason();
		this.description = customErrors.getDescription();
		this.timeStamp = AsyncUtil.convertTimestampToString(AsyncUtil.generateTimestamp());
		this.className = className;
		this.methodName = methodName;
	}

	public ErrorResponse(AsyncCustomException licenseManagementCustomException) throws ParseException {
		this.transactionId = licenseManagementCustomException.getTransactionId();
		this.errorCode = licenseManagementCustomException.getErrorCode();
		this.severity = licenseManagementCustomException.getSeverity();
		this.errorType = licenseManagementCustomException.getErrorType();
		this.reason = licenseManagementCustomException.getReason();
		this.description = licenseManagementCustomException.getDescription();
		this.timeStamp = licenseManagementCustomException.getTimeStamp();
	}

}
