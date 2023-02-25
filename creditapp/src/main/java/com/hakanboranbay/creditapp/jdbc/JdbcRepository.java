package com.hakanboranbay.creditapp.jdbc;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;

public class JdbcRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public int saveCreditApplication(CreditApplication creditApplication) {
		return jdbcTemplate.update("INSERT INTO credit_db.credit_application(clientIdNo, isSuccessful, creditLimit, applicationDate) VALUES (?,?,?,?)", 
				new Object[] { creditApplication.getClientIdNo(), creditApplication.isSuccessful(), creditApplication.getCreditLimit(), creditApplication.getApplicationDate() });
	}

}
