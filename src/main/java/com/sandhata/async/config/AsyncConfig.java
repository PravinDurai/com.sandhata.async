package com.sandhata.async.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "config", ignoreUnknownFields = true, ignoreInvalidFields = true)
@Data
@Component
public class AsyncConfig {

	// kafka config
	private String topicName;

	private String groupName;

	private String env;

	private String camundaRetryCount;

	private String enableKafka;

}
