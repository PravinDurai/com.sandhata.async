package com.sandhata.async.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.common.Uuid;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.sandhata.async.config.AsyncAppConfig;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.models.Address;
import com.sandhata.async.models.AsyncResponseModel;
import com.sandhata.async.models.Contact;
import com.sandhata.async.util.AsyncUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AsyncBak {

	private final ObjectMapper objectMapper;

	private final AsyncUtil asyncUtil;

	private final AsyncAppConfig asyncConfig;

	public AsyncBak(ObjectMapper objectMapper, AsyncUtil asyncUtil, AsyncAppConfig asyncConfig) {
		this.objectMapper = objectMapper;
		this.asyncUtil = asyncUtil;
		this.asyncConfig = asyncConfig;
	}

	public List<AsyncResponseModel> retrieveAsyncResponse(String transactionId, int count)
			throws JsonProcessingException, InterruptedException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_BAK,
				AsyncConstants.RETRIEVE_SYNC_RESPONSE, AsyncConstants.START);

		List<AsyncResponseModel> asyncResponseModels = new ArrayList<AsyncResponseModel>();

		for (int i = 0; i < count; i++) {
			asyncResponseModels.add(generateAsyncResponseModel());
		}

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_BAK, AsyncConstants.RETRIEVE_SYNC_RESPONSE,
				AsyncConstants.TYPE, AsyncConstants.BACKEND,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(asyncResponseModels));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_BAK,
				AsyncConstants.RETRIEVE_SYNC_RESPONSE, AsyncConstants.END);

		return asyncResponseModels;
	}

	private AsyncResponseModel generateAsyncResponseModel() throws InterruptedException {
		Faker faker = AsyncUtil.getFaker();

		AsyncResponseModel asyncResponseModel = new AsyncResponseModel();

		asyncResponseModel.setEmpId(Uuid.randomUuid().toString());
		asyncResponseModel.setFirstName(faker.name().firstName());
		asyncResponseModel.setLastName(faker.name().lastName());
		asyncResponseModel.setFullName(faker.name().fullName());

		Contact contact = new Contact();
		contact.setEmail(faker.internet().emailAddress());
		contact.setMobile(faker.phoneNumber().cellPhone());
		contact.setEmergencyContact(faker.phoneNumber().phoneNumber());

		Address address = new Address();
		address.setAddressLine1(faker.address().streetAddress());
		address.setAddressLine2(faker.address().secondaryAddress());
		address.setPostCode(faker.address().zipCode());
		address.setCounty(faker.address().cityName());
		address.setCounty(faker.address().country());

		address.setContact(contact);

		asyncResponseModel.setAddress(address);

		List<String> skillSet = new ArrayList<>();
		skillSet.add(faker.job().keySkills());
		skillSet.add(faker.job().keySkills());

		asyncResponseModel.setSkillSet(skillSet);

		asyncUtil.sleepThread(asyncConfig.getSleepTime().intValue());

		return asyncResponseModel;
	}

	@Async
	public CompletableFuture<List<AsyncResponseModel>> getAllUserInfoAsyncBak(String transactionId, Integer count)
			throws InterruptedException, JsonProcessingException {
		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_BAK,
				AsyncConstants.GET_ALL_USER_INFO_ASYNC_BAK, AsyncConstants.END);

		List<AsyncResponseModel> results = retrieveAsyncResponse(transactionId, count);

		log.info(AsyncConstants.OUT, transactionId, AsyncConstants.ASYNC_BAK,
				AsyncConstants.GET_ALL_USER_INFO_ASYNC_BAK, AsyncConstants.TYPE, AsyncConstants.BACKEND,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(results));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.ASYNC_BAK,
				AsyncConstants.GET_ALL_USER_INFO_ASYNC_BAK, AsyncConstants.END);

		return CompletableFuture.completedFuture(results);
	}

}
