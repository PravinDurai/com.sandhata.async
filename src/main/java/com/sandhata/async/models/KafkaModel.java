package com.sandhata.async.models;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Validated
@lombok.Generated
@Data
@ToString
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaModel {

	@JsonProperty("Environment")
	private String env;

	@JsonProperty("Transaction Id")
	private String transactionId;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Timestamp")
	private String timestamp;

	@JsonProperty("Topic Name")
	private String topicName;

	@JsonProperty("Request Type")
	private String requestType;

	@JsonProperty("Group Id")
	private String groupId;

}
