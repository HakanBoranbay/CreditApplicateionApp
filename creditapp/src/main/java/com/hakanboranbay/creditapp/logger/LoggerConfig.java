package com.hakanboranbay.creditapp.logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

	@Bean
    public Logger logger() {
        return new Logger();
    }
	
}
