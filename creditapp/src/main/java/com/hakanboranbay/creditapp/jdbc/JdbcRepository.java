package com.hakanboranbay.creditapp.jdbc;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hakanboranbay.creditapp.model.Client;
import com.hakanboranbay.creditapp.model.CreditApplication;

public class JdbcRepository implements IJdbcRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Client client) {
		return jdbcTemplate.update("INSERT INTO credit_db.client(idNo, name, surname, monthlyIncome, phoneNo, dateOfBirth, creditScore, lastModified) VALUES (?,?,?,?,?,?,?,?)", 
				new Object[] { client.getIdNo(), client.getName(), client.getSurname(),
						client.getMonthlyIncome(), client.getPhoneNo(), LocalDate.parse(client.getDateOfBirth()), client.getCreditScore(), client.getLastModified()});
	}

	@Override
	public int update(Client client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client findById(String idNo) {
		return jdbcTemplate.queryForObject("SELECT * FROM credit_db.client WHERE idNo=?",
                BeanPropertyRowMapper.newInstance(Client.class), idNo);
	}


	@Override
	public int deleteById(String idNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveCreditApplication(CreditApplication creditApplication) {
		return jdbcTemplate.update("INSERT INTO credit_db.credit_application(clientIdNo, isSuccessful, creditLimit, applicationDate) VALUES (?,?,?,?)", 
				new Object[] { creditApplication.getClientIdNo(), creditApplication.isSuccessful(), creditApplication.getCreditLimit(), creditApplication.getApplicationDate() });
	}

}
