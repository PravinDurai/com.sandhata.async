package com.sandhata.async.exception.handler;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.exception.AsyncCustomException;
import com.sandhata.async.exception.ErrorResponse;
import com.sandhata.async.util.AsyncUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AsyncExceptionHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AsyncUtil asyncUtil;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exc)
			throws JsonProcessingException, ParseException {

		log.info(AsyncConstants.START_END, AsyncConstants.ASYNC_EXCEPTION_HANDLER,
				AsyncConstants.GENERIC_EXCEPTION_HANDLER, AsyncConstants.START);

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTransactionId(AsyncConstants.UNKNOWN);
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorResponse.setReason(exc.getLocalizedMessage());
		errorResponse.setErrorType(AsyncConstants.TECHNICAL);
		errorResponse.setDescription(exc.getMessage());
		errorResponse.setSeverity(AsyncConstants.HIGH);
		errorResponse.setTimeStamp(AsyncUtil.convertTimestampToString(AsyncUtil.generateTimestamp()));

		ResponseEntity<ErrorResponse> errorResponseEntity = new ResponseEntity<ErrorResponse>(errorResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);

		log.error(AsyncConstants.ERROR_LOG, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exc));

		asyncUtil.pushMessageToKafka(AsyncConstants.UNKNOWN,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponseEntity),
				AsyncConstants.ERROR_RESPONSE);

		log.error(AsyncConstants.OUT, AsyncConstants.UNKNOWN, AsyncConstants.ASYNC_EXCEPTION_HANDLER,
				AsyncConstants.GENERIC_EXCEPTION_HANDLER, AsyncConstants.FAILED, AsyncConstants.RESPONSE,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponseEntity));

		log.info(AsyncConstants.START_END, AsyncConstants.ASYNC_EXCEPTION_HANDLER,
				AsyncConstants.GENERIC_EXCEPTION_HANDLER, AsyncConstants.END);

		return errorResponseEntity;
	}

	@ExceptionHandler(AsyncCustomException.class)
	public ResponseEntity<ErrorResponse> asyncCustomExceptionHandler(AsyncCustomException exc)
			throws JsonProcessingException, ParseException {

		log.info(AsyncConstants.START_END, AsyncConstants.ASYNC_EXCEPTION_HANDLER,
				AsyncConstants.ASYNC_CUSTOME_EXCEPTION_HANDLER, AsyncConstants.START);

		ErrorResponse errorResponse = new ErrorResponse(exc);

		ResponseEntity<ErrorResponse> errorResponseEntity = null;

		switch (exc.getErrorCode()) {

		case AsyncConstants.STATUS_404:
		case AsyncConstants.STATUS_400:
			new ResponseEntity<ErrorResponse>(errorResponse, asyncUtil.getResponseHeader(exc.getTransactionId()),
					HttpStatus.BAD_REQUEST);
			break;
		case AsyncConstants.STATUS_500:
			new ResponseEntity<ErrorResponse>(errorResponse, asyncUtil.getResponseHeader(exc.getTransactionId()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.error(AsyncConstants.ERROR_LOG, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exc));

		asyncUtil.pushMessageToKafka(exc.getTransactionId(),
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponseEntity),
				AsyncConstants.ERROR);

		log.error(AsyncConstants.OUT, exc.getTransactionId(), AsyncConstants.ASYNC_EXCEPTION_HANDLER,
				AsyncConstants.ASYNC_CUSTOME_EXCEPTION_HANDLER, AsyncConstants.FAILED,
				AsyncConstants.RESPONSE,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponseEntity));

		log.info(AsyncConstants.START_END, AsyncConstants.ASYNC_EXCEPTION_HANDLER,
				AsyncConstants.ASYNC_CUSTOME_EXCEPTION_HANDLER, AsyncConstants.END);

		return errorResponseEntity;
	}

}
