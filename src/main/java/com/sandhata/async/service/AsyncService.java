package com.sandhata.async.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sandhata.async.models.AsyncResponseModel;

public interface AsyncService {

	public ResponseEntity<List<AsyncResponseModel>> getAllEmployeeInfoService(String transactionId);

}
