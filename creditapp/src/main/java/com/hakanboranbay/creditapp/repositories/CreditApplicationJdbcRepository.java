package com.hakanboranbay.creditapp.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;

@Component
public class CreditApplicationJdbcRepository implements ICreditApplicationRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String CREATE_CREDIT_APPLICATION_SQL = "INSERT INTO credit_db.credit_application"
			+ "(clientIdNo, clientMonthlyIncome, clientCreditScore, applicationDate, isSuccessful, creditLimit) VALUES (?,?,?,?,?,?)";
	private final String LIST_CLIENT_CREDIT_APPLICATIONS_SQL = "SELECT * FROM credit_db.credit_application WHERE clientIdNo IN (SELECT idNo FROM credit_db.client c WHERE c.dateOfBirth=?)";

	@Override
	public int createCreditApplication(CreditApplication creditApplication) {
		return jdbcTemplate.update(CREATE_CREDIT_APPLICATION_SQL, 
				new Object[] { creditApplication.getClientIdNo(), 
						creditApplication.getClientMonthlyIncome(), 
						creditApplication.getClientCreditScore(),
						creditApplication.getApplicationDate(), 
						creditApplication.isSuccessful(), 
						creditApplication.getCreditLimit()});
	}

	@Override
	public List<CreditApplication> listClientCreditApplications(Client client) {
		Date dateOfBirth = Date.valueOf(client.getDateOfBirth());
		return jdbcTemplate.query(LIST_CLIENT_CREDIT_APPLICATIONS_SQL,
		        BeanPropertyRowMapper.newInstance(CreditApplication.class), dateOfBirth);
	}
	
}
