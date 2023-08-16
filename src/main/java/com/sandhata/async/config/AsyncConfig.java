package com.sandhata.async.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

	private final AsyncAppConfig asyncAppConfig;

	public AsyncConfig(AsyncAppConfig asyncAppConfig) {
		this.asyncAppConfig = asyncAppConfig;
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(asyncAppConfig.getCorePoolSize().intValue());
		executor.setMaxPoolSize(asyncAppConfig.getMaxPoolSize().intValue());
		executor.setQueueCapacity(asyncAppConfig.getQueueCapacity().intValue());
		executor.setThreadNamePrefix(asyncAppConfig.getThreadNamePrefix());
		executor.initialize();
		return executor;
	}

}
