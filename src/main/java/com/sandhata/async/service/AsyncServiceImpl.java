package com.sandhata.async.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandhata.async.backend.AsyncBak;
import com.sandhata.async.config.AsyncAppConfig;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.models.AsyncResponseModel;
import com.sandhata.async.util.AsyncUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

	private final ObjectMapper objectMapper;

	private final AsyncBak asyncBak;

	private final AsyncUtil asyncUtil;

	private final AsyncAppConfig asyncAppConfig;

	private final Executor executor;

	public AsyncServiceImpl(ObjectMapper objectMapper, AsyncBak asyncBak, AsyncUtil asyncUtil,
			AsyncAppConfig asyncAppConfig, Executor executor) {
		this.objectMapper = objectMapper;
		this.asyncBak = asyncBak;
		this.asyncUtil = asyncUtil;
		this.asyncAppConfig = asyncAppConfig;
		this.executor = executor;
	}

	@Override
	public ResponseEntity<List<AsyncResponseModel>> getAllEmployeeInfoSyncService(String transactionId, int count)
			throws JsonProcessingException, InterruptedException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SYNC_SERVICE, AsyncConstants.START);

		List<AsyncResponseModel> asyncResponseModels = asyncBak.retrieveAsyncResponse(transactionId, count);

		ResponseEntity<List<AsyncResponseModel>> response = new ResponseEntity<>(asyncResponseModels,
				asyncUtil.getResponseHeader(transactionId), HttpStatus.OK);

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SYNC_SERVICE, AsyncConstants.TYPE, AsyncConstants.SERVICE,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SYNC_SERVICE, AsyncConstants.END);

		return response;
	}

	@Override
	public ResponseEntity<String> getAllEmployeeInfoService(String transactionId, Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException {
		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.START);

		ResponseEntity<String> response = new ResponseEntity<>(null, asyncUtil.getResponseHeader(transactionId),
				HttpStatus.ACCEPTED);

		CompletableFuture<List<AsyncResponseModel>> asyncCompletableFuture = asyncBak
				.getAllUserInfoAsyncBak(transactionId, count);

		if (asyncCompletableFuture.isDone()) {
			log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_SERVICE,
					AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.TYPE, AsyncConstants.SERVICE,
					objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(asyncCompletableFuture.get()));
		}

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.END);

		return response;
	}

	@Override
	public ResponseEntity<String> getAllEmployeeInfoUsingExecutorService(String transactionId, Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException {
		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SERVICE, AsyncConstants.START);

		ResponseEntity<String> response = new ResponseEntity<>(null, asyncUtil.getResponseHeader(transactionId),
				HttpStatus.ACCEPTED);

		ExecutorService executorService = Executors.newFixedThreadPool(asyncAppConfig.getMaxPoolSize().intValue());

		executorService.submit(() -> {

			log.info("Thread :: current thread name : {}", Thread.currentThread().getName());

			CompletableFuture<List<AsyncResponseModel>> asyncCompletableFuture = asyncBak
					.getAllUserInfoAsyncBak(transactionId, count);

			if (Objects.nonNull(asyncCompletableFuture.get())) {

				log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_SERVICE,
						AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SERVICE, AsyncConstants.TYPE,
						AsyncConstants.SERVICE,
						objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(asyncCompletableFuture.get()));
			}

			return asyncCompletableFuture;

		});

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SERVICE, AsyncConstants.END);

		return response;
	}

	@Override
	public ResponseEntity<String> getAllEmployeeInfoUsingExecutorSupplyAsyncService(String transactionId, Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException {
		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_SERVICE, AsyncConstants.START);

		ResponseEntity<String> response = new ResponseEntity<>(null, asyncUtil.getResponseHeader(transactionId),
				HttpStatus.ACCEPTED);

		CompletableFuture<List<AsyncResponseModel>> asyncCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				return asyncBak.retrieveAsyncResponse(transactionId, count);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}, executor);

		asyncCompletableFuture.whenComplete((info, err) -> {
			if (Objects.nonNull(info) && !info.isEmpty()) {
				try {
					log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_SERVICE,
							AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_SERVICE,
							AsyncConstants.TYPE, AsyncConstants.SERVICE,
							objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(info));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				err.printStackTrace();
			}
		});

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_SERVICE, AsyncConstants.END);

		return response;
	}

}
