package com.sandhata.async.service;

import org.springframework.http.ResponseEntity;

import com.sandhata.async.models.AsyncResponseModel;

import reactor.core.publisher.Flux;

public interface ReactorService {

	public ResponseEntity<Flux<AsyncResponseModel>> getAllEmployeeInfoService(String transactionId);

}
