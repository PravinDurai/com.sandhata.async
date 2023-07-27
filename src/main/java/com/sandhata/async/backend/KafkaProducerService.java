package com.sandhata.async.backend;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandhata.async.config.AsyncConfig;
import com.sandhata.async.constant.AsyncConstants;
import com.sandhata.async.models.KafkaModel;
import com.sandhata.async.util.AsyncUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private AsyncConfig asyncConfig;

	@Autowired
	private ObjectMapper objectMapper;

	public void pushMessageToKafka(String transactionId, String message, String requestType)
			throws JsonProcessingException {

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.KAFKA_PRODUCER_SERVICE,
				AsyncConstants.PUSH_MESSAGE_TO_KAFKA, AsyncConstants.START);

		KafkaModel kafkaModel = new KafkaModel();

		kafkaModel.setEnv(asyncConfig.getEnv());

		kafkaModel.setGroupId(asyncConfig.getGroupName());

		kafkaModel.setMessage(message);

		kafkaModel.setTransactionId(transactionId);

		kafkaModel.setRequestType(requestType);

		try {
			kafkaModel.setTimestamp(AsyncUtil.convertTimestampToString(AsyncUtil.generateTimestamp()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (asyncConfig.getEnableKafka().equals(AsyncConstants.TRUE_STRING)) {
			kafkaTemplate.send(asyncConfig.getTopicName(), AsyncConstants.BASE_PACKAGE,
					objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(kafkaModel));
		}

		log.info(transactionId, AsyncConstants.OUT, AsyncConstants.KAFKA_PRODUCER_SERVICE,
				AsyncConstants.PUSH_MESSAGE_TO_KAFKA, AsyncConstants.TYPE, AsyncConstants.KAFKA,
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(kafkaModel));

		log.info(AsyncConstants.START_END, transactionId, AsyncConstants.KAFKA_PRODUCER_SERVICE,
				AsyncConstants.PUSH_MESSAGE_TO_KAFKA, AsyncConstants.END);
	}

}
