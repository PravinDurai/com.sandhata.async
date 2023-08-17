package com.sandhata.async.rest;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private final AsyncUtil asyncUtil;

	private final ObjectMapper objectMapper;

	private final AsyncService asyncService;

	private final ReactorService reactorService;

	public AsyncController(AsyncUtil asyncUtil, ObjectMapper objectMapper, AsyncService asyncService,
			ReactorService reactorService) {
		this.asyncUtil = asyncUtil;
		this.objectMapper = objectMapper;
		this.asyncService = asyncService;
		this.reactorService = reactorService;
	}

	@GetMapping(value = "/normal/all-employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<AsyncResponseModel>> getAllEmployeeInfoSyncRest(
			@RequestHeader(value = AsyncConstants.TRANSACTION_ID, required = true) String transactionId,
			@RequestParam(value = AsyncConstants.COUNT, required = true) Integer count)
			throws JsonProcessingException, InterruptedException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SYNC_REST, AsyncConstants.START);

		ResponseEntity<List<AsyncResponseModel>> response = asyncService.getAllEmployeeInfoSyncService(transactionId,
				count);

		asyncUtil.pushMessageToKafka(transactionId,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response),
				AsyncConstants.RESPONSE_OUT);

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SYNC_REST, AsyncConstants.TYPE, AsyncConstants.REST,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SYNC_REST, AsyncConstants.END);

		return response;
	}

	@GetMapping(value = "/annotation/all-employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> getAllEmployeeInfoRest(
			@RequestHeader(value = AsyncConstants.TRANSACTION_ID, required = true) String transactionId,
			@RequestParam(value = AsyncConstants.COUNT, required = true) Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REST, AsyncConstants.START);

		ResponseEntity<String> response = asyncService.getAllEmployeeInfoService(transactionId, count);

		asyncUtil.pushMessageToKafka(transactionId,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response),
				AsyncConstants.RESPONSE_OUT);

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REST, AsyncConstants.TYPE, AsyncConstants.REST,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_REST, AsyncConstants.END);

		return response;
	}

	@GetMapping(value = "/annotation/executor/all-employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> getAllEmployeeInfoUsingExecutorRest(
			@RequestHeader(value = AsyncConstants.TRANSACTION_ID, required = true) String transactionId,
			@RequestParam(value = AsyncConstants.COUNT, required = true) Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_REST, AsyncConstants.START);

		ResponseEntity<String> response = asyncService.getAllEmployeeInfoUsingExecutorService(transactionId, count);

		asyncUtil.pushMessageToKafka(transactionId,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response),
				AsyncConstants.RESPONSE_OUT);

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_REST, AsyncConstants.TYPE, AsyncConstants.REST,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_REST, AsyncConstants.END);

		return response;
	}

	@GetMapping(value = "/annotation/executor/supply-async/all-employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> getAllEmployeeInfoUsingExecutorSupplyAsyncRest(
			@RequestHeader(value = AsyncConstants.TRANSACTION_ID, required = true) String transactionId,
			@RequestParam(value = AsyncConstants.COUNT, required = true) Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_REST, AsyncConstants.START);

		ResponseEntity<String> response = asyncService.getAllEmployeeInfoUsingExecutorSupplyAsyncService(transactionId,
				count);

		asyncUtil.pushMessageToKafka(transactionId,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response),
				AsyncConstants.RESPONSE_OUT);

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_REST, AsyncConstants.TYPE,
				AsyncConstants.REST, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_CONTROLLER,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_REST, AsyncConstants.END);

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
