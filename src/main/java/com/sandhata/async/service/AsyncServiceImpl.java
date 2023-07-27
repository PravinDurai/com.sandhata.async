package com.sandhata.async.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.models.AsyncResponseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ResponseEntity<List<AsyncResponseModel>> getAllEmployeeInfoService(String transactionId) {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.START);

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_SERVICE,
				AsyncConstants.GET_ALL_EMPLOYEE_INFO_SERVICE, AsyncConstants.END);

		return null;
	}

}
