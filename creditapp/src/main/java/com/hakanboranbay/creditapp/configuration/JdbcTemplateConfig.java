package com.hakanboranbay.creditapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hakanboranbay.creditapp.jdbc.JdbcRepository;

@Configuration
public class JdbcTemplateConfig {
	
	@Bean
    public JdbcRepository jdbcRepository() {
        return new JdbcRepository();
    }

}
