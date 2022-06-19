package com.revature;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy // Enable the use of the @Aspect annotation
public class LogAggregationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAggregationDemoApplication.class, args);
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) { return new TimedAspect(registry); }

	@Bean
	public CountedAspect countedAspect(MeterRegistry registry) { return new CountedAspect(registry); }

}
