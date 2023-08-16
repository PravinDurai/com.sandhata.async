package com.sandhata.async.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sandhata.async.models.AsyncResponseModel;

public interface AsyncService {

	public ResponseEntity<List<AsyncResponseModel>> getAllEmployeeInfoSyncService(String transactionId, int count)
			throws JsonProcessingException, InterruptedException;
	
	public ResponseEntity<String> getAllEmployeeInfoService(String transactionId, Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException;
	
	public ResponseEntity<String> getAllEmployeeInfoUsingExecutorService(String transactionId, Integer count)
			throws JsonProcessingException, InterruptedException, ExecutionException;


}
