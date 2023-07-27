package com.sandhata.async.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sandhata.async.backend.KafkaProducerService;
import com.sandhata.async.constant.AsyncConstants;

@Component
public class AsyncUtil {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	public static Timestamp generateTimestamp() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	public static Timestamp convertStringToTimestamp(String strDate) throws ParseException {
		return Timestamp.valueOf(strDate);
	}

	public static String convertTimestampToString(Timestamp timestamp) throws ParseException {
		if (timestamp != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(AsyncConstants.SQL_DATE_FORMAT);
			return sdf.format(new Date(timestamp.getTime()));
		}
		return null;
	}

	public HttpHeaders getResponseHeader(String transactionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AsyncConstants.X_TRANSACTION_ID, transactionId);
		return headers;
	}

	public void pushMessageToKafka(String transactionId, String message, String requestType)
			throws JsonProcessingException {
		kafkaProducerService.pushMessageToKafka(transactionId, message, requestType);
	}

}
