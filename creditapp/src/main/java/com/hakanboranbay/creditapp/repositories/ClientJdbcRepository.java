package com.hakanboranbay.creditapp.repositories;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hakanboranbay.creditapp.model.Client;

@Component
public class ClientJdbcRepository implements IClientRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String CREATE_CLIENT_SQL = "INSERT INTO credit_db.client(idNo, name, surname, monthlyIncome, phoneNo, dateOfBirth, creditScore, lastModified) VALUES (?,?,?,?,?,?,?,?)";
	private final String GET_DETAILS_BY_ID_SQL = "SELECT * FROM credit_db.client WHERE idNo=?";
	private final String UPDATE_CLIENT_SQL = "UPDATE credit_db.client SET creditScore=?, monthlyIncome=? WHERE idNo=?";
	private final String DELETE_CLIENT_SQL = "DELETE FROM credit_db.client WHERE idNo=?";
	
	@Override
	public int create(Client client) {
		return jdbcTemplate.update(CREATE_CLIENT_SQL, 
				new Object[] { client.getIdNo(), 
						client.getName(), 
						client.getSurname(),
						client.getMonthlyIncome(), 
						client.getPhoneNo(), 
						LocalDate.parse(client.getDateOfBirth()), 
						client.getCreditScore(), 
						client.getLastModified()});
	}

	@Override
	public Client getDetailsById(String clientIdNo) {
		return jdbcTemplate.queryForObject(GET_DETAILS_BY_ID_SQL,
                BeanPropertyRowMapper.newInstance(Client.class), clientIdNo);
	}

	@Override
	public int updateClient(Client client, int creditScore, double monthlyIncome) {
		return jdbcTemplate.update(UPDATE_CLIENT_SQL,
				new Object[] { client.getCreditScore(),
						client.getMonthlyIncome(),
						client.getIdNo()});
	}

	@Override
	public int deleteClient(Client client) {
		return jdbcTemplate.update(DELETE_CLIENT_SQL, client.getIdNo());
	}

}
