package com.sandhata.async.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.models.AsyncResponseModel;
import com.sandhata.async.service.AsyncService;
import com.sandhata.async.service.ReactorService;
import com.sandhata.async.util.AsyncUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

	@Autowired
	private AsyncUtil asyncUtil;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private ReactorService reactorService;

	@GetMapping(value = "/annotation/all-employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<AsyncResponseModel>> getAllEmployeeInfoRest(
			@RequestHeader(value = AsyncConstants.TRANSACTION_ID, required = true) String transactionId)
			throws JsonProcessingException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REST, AsyncConstants.START);

		ResponseEntity<List<AsyncResponseModel>> response = asyncService.getAllEmployeeInfoService(transactionId);

		asyncUtil.pushMessageToKafka(transactionId,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response),
				AsyncConstants.RESPONSE_OUT);

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REST, AsyncConstants.END);

		return response;
	}

	@GetMapping(value = "/reactor/all-employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Flux<AsyncResponseModel>> getAllEmployeeInfoReactor(
			@RequestHeader(value = AsyncConstants.TRANSACTION_ID, required = true) String transactionId)
			throws JsonProcessingException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REACTOR, AsyncConstants.START);

		ResponseEntity<Flux<AsyncResponseModel>> response = reactorService.getAllEmployeeInfoService(transactionId);

		asyncUtil.pushMessageToKafka(transactionId,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response),
				AsyncConstants.RESPONSE_OUT);

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REACTOR, AsyncConstants.END);

		return response;
	}

}
