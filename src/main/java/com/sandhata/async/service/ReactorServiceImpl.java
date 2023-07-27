package com.sandhata.async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.models.AsyncResponseModel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class ReactorServiceImpl implements ReactorService {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ResponseEntity<Flux<AsyncResponseModel>> getAllEmployeeInfoService(String transactionId) {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.REACTOR_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.START);

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.REACTOR_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.END);

		return null;
	}

}
