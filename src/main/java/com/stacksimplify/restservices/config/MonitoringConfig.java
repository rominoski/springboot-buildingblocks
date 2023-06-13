package com.stacksimplify.restservices.config;

import org.springframework.context.annotation.Configuration;

import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.common.lang.Nullable;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MonitoringConfig {

	AppOpticsConfig appopticsConfig = new AppOpticsConfig() {
	    @Override
	    public String apiToken() {
	        return "2pUAxeRNfoRflzOZ5NbbxTDyCDRnkM22wi-9omADm0oRDwD9KW6aB8WTNSDg7HNS9ZyerWU";
	    }

	    @Override
	    @Nullable
	    public String get(String k) {
	        return null;
	    }
	};
	MeterRegistry registry = new AppOpticsMeterRegistry(appopticsConfig, Clock.SYSTEM);
}
